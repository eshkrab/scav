#!/usr/bin/env python2.7
"""
@about Flask server for the Scav app
@author Placeholder Studios
"""

from __future__ import print_function
import json, hashlib, os, pprint, re, random, smtplib, traceback
from email.mime.text import MIMEText
#from multiprocessing import Pool
from flask import Flask, request

# pool = Pool(processes=1)

app = Flask(__name__)

database = {}
dbfile = "scavdb.json"

access_key = open('acess_key').read()

success_message = json.dumps({"status" : "success"})

# various error messages
illegal_access_key_error = json.dumps({'status': 'error', 'name': 'illegal_access_key_error', 'message': 'illegal access key'})

no_user_error = json.dumps({'status': 'error', 'name': 'no_user_error', 'message': 'no such user'})
duplicate_user_error = json.dumps({'status': 'error', 'name': 'duplicate_user_error', 'message': 'user already exists'})
login_incorrect_error = json.dumps({'status': 'error', 'name': 'login_incorrect_error', 'message': 'login data incorrect'})
user_not_verified_error = json.dumps({'status': 'error', 'name': 'user_not_verified_error', 'message': 'you haven\'t verified your CNetID'})

no_team_error = json.dumps({'status': 'error', 'name': 'no_team_error', 'message': 'no such team'})
duplicate_team_error = json.dumps({'status': 'error', 'name': 'duplicate_team_error', 'message': 'team already exists'})

no_item_error = json.dumps({'status': 'error', 'name': 'no_item_error', 'message': 'no such item'})
duplicate_item_error = json.dumps({'status': 'error', 'name': 'duplicate_item_error', 'message': 'item already exists'})
item_status_error = json.dumps({'status': 'error', 'name': 'item_status_error', 'message': 'status must be available, in progress or done'})
item_owner_error = json.dumps({'status': 'error', 'name': 'item_owner_error', 'message': 'item already has an owner or you are trying to pass an owner that isn\'t in users'})

# allowed item statuses
allowed_item_statuses = {'available', 'in progress', 'done'}

# regular expressions for parsing phone numbers
num_nice_re = re.compile('[\(\)\+\- ]')
num_len_re = re.compile('\d{10,11}')

# async decorator
# def async(func):
# 	def wrapper(*args):
# 		pool.apply_async(func, args)
# 	return wrapper  

def save_database():
	json.dump(database, open(dbfile, "w"))

def load_database():
	global database
	try:
		database = json.load(open(dbfile))
	except IOError:
		database = {"users" : {},
                    "teams" : {},
                    "items" : {}}

def hashify(password):
	"""
	Creates and returns a hex sha256 hash of the string that comes in
	"""
	hashie = hashlib.sha256()
	hashie.update(password.encode('utf-8'))
	pass_hash = hashie.hexdigest()
	return pass_hash

# this will need to be async'ed, hopefully, unless I find out that it doesn't
def send_verification(cnetid):
	# make the hash
	h = hashify(str(random.random()))
	# compose the email
	content = """\
<html>
	<head></head>
	<body>
		<p>Hi!
		<br>
		<br>
		You are receiving this because someone, and it could've even been you, entered your CNetID into our app.<br><br>
		If it was indeed you, go ahead and click <a href="{link}">this link</a>.<br>
		If it was not you, alert the authorities immediately and don't leave your place of residence.<br><br>
		Oh, and also please don't reply to this email, because then the universe might implode, and then what do we do?<br>
		<br><br>
		Bye-bye!
		<br><br>
		Your faithful Scav app server
	</body>
</html>
""".format(link='http://raspi.ostensible.me:5000/verify_cnet?cnetid={}&hash={}'.format(cnetid, h))
	msg = MIMEText(content, 'html')
	msg['Subject'] = 'The email that you\'ve been waiting for'
	msg['From'] = smtp_user
	msg['To'] = '{}@uchicago.edu'.format(cnetid)
	# establish the SMTP connection
	try:
		s = smtplib.SMTP_SSL(smtp_server)
		s.connect(smtp_server)
		s.ehlo()
		s.login(smtp_user, smtp_pass)
		# send the email
		s.sendmail(smtp_user, msg['To'], msg.as_string())
	except Exception as e:
		print(e)
	else:
		database['users'][cnetid]['verification_hash'] = h
		save_database()

@app.route("/")
def home():
	return "Hi. I'm a tiny flask server running to demo the scav app. There isn't anything to see here, really."

@app.route("/createItem", methods = ['POST'])
def create_item():
	"""
	Needs: access_key, number, description, points, due_date
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	number = cur_request['number']
	if number in database['items']:
		return duplicate_item_error
	description = cur_request['description']
	points = cur_request['points']
	due_date = cur_request['due_date']
	database['items'][number] = {'description': description, 'points' : points, 'status': {}, 'due_date': due_date, 'owner': {}}
	print('creating item: {0}'.format(number))
	save_database()
	return success_message

@app.route("/createTeam", methods = ['POST'])
def create_team():
	"""
	Needs: access_key, team (name), captain (cnet)
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	team = cur_request['team']
	captain = cur_request['captain']
	if team in database['teams']:
		return duplicate_team_error
	database["teams"][team] = {'captain': captain, 'members': []}
	# iterate over items to add team statuses for the team
	for item in database['items']:
		database['items'][item]['status'][team] = 'available'
	# print('creating team: {0}'.format(team))
	save_database()
	return success_message

@app.route("/createUser", methods = ['POST'])
def create_user():
	"""
	Needs: access_key, cnetid, password
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	cnetid = cur_request['cnetid']
	if cnetid in database['users'] and database['users'][cnetid]['verified'] == True:
		return duplicate_user_error
	pass_hash = hashify(cur_request['password'])
	email = cnetid + '@uchicago.edu'
	database['users'][cnetid] = {
								'email': email,
								'pass_hash': pass_hash,
								'verified': False, 
								'team': None,
								'about': None,
								'phone_number': None}
	print('creating user: {0}'.format(cnetid))
	send_verification(cnetid)
	save_database()
	return success_message

@app.route('/edit_user', methods=['POST'])
def edit_user():
	"""
	Needs: access_key, cnetid, password, new_password, team, about, phone_number
	"""
	cur_request = request.form if request.json is None else request.json
	# check the access key
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	cnetid = cur_request['cnetid']
	# check if they exist
	try:
		user = database['users'][cnetid]
	except KeyError:
		return no_user_error
	password = cur_request['password']
	# check if the password is valid
	if hashify(password) != user['pass_hash'] and password != user['pass_hash']:
		return login_incorrect_error
	# check if they're verified
	if user['verified'] != True:
		return user_not_verified_error
	# if they want to change their password
	try:
		new_password = cur_request['new_password']
		if new_password != '':
			new_pass_hash = hashify(new_password)
			user['pass_hash'] = new_pass_hash
	# if they don't
	except KeyError:
		pass
	team = cur_request['team']
	# check if the team exists
	if team not in database["teams"] and team != '':
		return no_team_error
	about = cur_request['about']
	try:
		phone_number = str(cur_request['phone_number'])
	# hopefully this won't happen, debug only
	except KeyError as e:
		pp = pprint.PrettyPrinter()
		pp.pprint(cur_request)
	# if it comes in as a nicely formatted number, we'll get rid of that nice formatting
	if num_nice_re.search(phone_number):
		phone_number = phone_number.translate(None, '+()- ')
	user['team'] = team
	user['about'] = about
	user['phone_number'] = phone_number
	database['teams'][team]['members'].append(cnetid)
	save_database()
	return success_message
	
@app.route('/getUser', methods=['POST'])
def get_user():
	"""
	Needs: access_key, cnetid, password
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	cnetid = cur_request['cnetid']
	password = cur_request['password']
	try:
		user = database['users'][cnetid]
	except KeyError:
		return no_user_error
	if hashify(password) == user['pass_hash'] or password == user['pass_hash']:
		return json.dumps(user)
	else:
		print('wrong password')
		print(cur_request)
		return login_incorrect_error

@app.route('/getTeam', methods=['POST'])
def get_team():
	"""
	Needs: access_key, teamname
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_acccess_key_error
	teamname = cur_request['team']
	try:
		team = database['teams'][teamname]
	except KeyError:
		return no_team_error
	return json.dumps(team)

@app.route('/getItem', methods=['POST'])
def get_item():
	"""
	Needs: access_key, number
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_acccess_key_error
	number = cur_request['number']
	try:
		item = database['items'][number]
	except KeyError:
		return no_item_error
	return json.dumps(item)

@app.route('/amendItem', methods=['POST'])
def amend_item():
	"""
	Needs: access_key, number, new_status, new_owner
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_acccess_key_error
	number = cur_request['number']
	try:
		item = database['items'][number]
	except KeyError:
		return no_item_error
	new_owner = cur_request['new_owner']
	# find the team of the new owner
	team_name = [team_name for team_name in database['teams'].keys() if new_owner in database['teams'][team_name]['members'] or new_owner == database['teams'][team_name]['captain']][0]
	# if the there is no team record for this item ownership, then there definitely is no owner
	try:
		present_owner = item['owner'][team_name]
	except KeyError:
		present_owner = ''
	# we can only assign an owner if there isn't one already
	if present_owner == '':
		item['owner'][team_name] = new_owner
	else:
		return item_owner_error
	new_status = cur_request['new_status']
	if item['status'][team_name] == 'available' and new_status in allowed_item_statuses:
		item['status'][team_name] = new_status
	else:
		return item_status_error
	database['items'][number] = item
	save_database()
	return success_message

@app.route("/getAllUsers")
def list_users():
	# so this most likely will need to be changed in the future because the current version does not check for access key, as it is a GET request
	return json.dumps(database["users"])

@app.route("/getTeams")
def list_teams():
	return json.dumps(database["teams"])

@app.route("/getItems")
def list_items():
	return json.dumps(database["items"])

@app.route('/verify_cnet')
def verify_cnet():
	cnetid = request.args.get('cnetid')
	h = request.args.get('hash')
	try:
		if database['users'][cnetid]['verification_hash'] == h:
			database['users'][cnetid]['verified'] = True
			del database['users'][cnetid]['verification_hash']
			save_database()
			response = "Thanks, you're all set."
		else:
			response = """\
			Sorry, but it looks like we couldn't verify you.
			<br><br>
			Try registering again, and please don't be mad!"""
			del database['users'][cnetid]
	except KeyError:
		response = """\
		We couldn't deal with your request. This either means that you're already verified, or are trying to do something that you weren't supposed to.
		<br><br>
		If you sense a disturbance in the Force, send us an <a href=\"mailto:{}\">email.</a>
		""".format('scav@ostensible.me')
	return response

if __name__ == "__main__":
	load_database()
	# get the server configuration
	server_config = json.load(open('serverconfig.json'))
	# get the smtp settings
	smtp_config = server_config['smtp']
	smtp_server = str(smtp_config['server'])
	smtp_port = str(smtp_config['port'])
	smtp_user = str(smtp_config['user'])
	smtp_pass = str(smtp_config['pass'])
	print('PID: {0}'.format(os.getpid()))
	app.run(debug=True, port=5000, host="0.0.0.0")