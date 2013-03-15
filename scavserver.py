#!/usr/bin/env python2.7
"""
@about Flask server for the Scav app
@author Placeholder Studios
"""

from __future__ import print_function
from flask import Flask, request
import json, hashlib, os, pprint, re

app = Flask(__name__)

database = {}
dbfile = "scavdb.json"

access_key = open('acess_key').read()

success_message = json.dumps({"status" : "success"})

# various error messages
illegal_access_key_error = json.dumps({'status': 'error', 'name': 'illegal_access_key_error', 'message': 'illegal access key'})

duplicate_user_error = json.dumps({'status': 'error', 'name': 'duplicate_user_error', 'message': 'user already exists'})
login_incorrect_error = json.dumps({'status': 'error', 'name': 'login_incorrect_error', 'message': 'login data incorrect'})

no_team_error = json.dumps({'status': 'error', 'name': 'no_team_error', 'message': 'no such team'})
duplicate_team_error = json.dumps({'status': 'error', 'name': 'duplicate_team_error', 'message': 'team already exists'})

no_item_error = json.dumps({'status': 'error', 'name': 'no_item_error', 'message': 'no such item'})
duplicate_item_error = json.dumps({'status': 'error', 'name': 'duplicate_item_error', 'message': 'item already exists'})
item_status_error = json.dumps({'status': 'error', 'name': 'item_status_error', 'message': 'status must be available, in progress or done'})
item_owner_error = json.dumps({'status': 'error', 'name': 'item_owner_error', 'message': 'item already has an owner or you are trying to pass an owner that isn\'t in users'})

no_user_error = json.dumps({'status': 'error', 'name': 'no_user_error', 'message': 'no such user'})

allowed_item_statuses = {'available', 'in progress', 'done'}

num_nice_re = re.compile('[\(\)\+\- ]')
num_len_re = re.compile('\d{10,11}')


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

@app.route("/")
def home():
	return "Hi. I'm a tiny flask server running to demo the scav app. There isn't anything to see here, really."

@app.route("/createUser", methods = ['POST'])
def create_user():
	"""
	Needs: access_key, cnetid, password, team, about, phone_number
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	cnetid = cur_request['cnetid']
	if cnetid in list(database['users'].keys()):
		return duplicate_user_error
	pass_hash = hashify(cur_request['password'])
	team = cur_request['team']
	if(team not in database["teams"]):
		return no_team_error
	about = cur_request['about']
	try:
		phone_number = str(cur_request['phone_number'])
	except KeyError as e:
		pp = pprint.PrettyPrinter()
		pp.pprint(cur_request)
		return json.dumps({'error': 'I fail'})
	# if it comes in as a nicely formatted number, we'll get rid of that nice formatting
	if num_nice_re.search(phone_number):
		phone_number = phone_number.translate(None, '+()- ')
	# let's check if it's valid, or if there is something there at all
	phone_number = phone_number if num_len_re.match(phone_number) else ''
	email = cnetid + '@uchicago.edu'
	database['users'][cnetid] = {'email': email, 'pass_hash': pass_hash, 'team': team, 'phone_number': phone_number, 'about': about}
	database['teams'][team]['members'].append(cnetid)
	print('creating user: {0}'.format(cnetid))
	print(database['users'])
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
	if team in list(database['teams'].keys()):
		return duplicate_team_error
	database["teams"][team] = {'captain': captain, 'members': []}
	print('creating team: {0}'.format(team))
	save_database()
	return success_message

@app.route("/createItem", methods = ['POST'])
def create_item():
	"""
	Needs: access_key, number, description, points, due_date
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	number = cur_request['number']
	if number in list(database['items'].keys()):
		return duplicate_item_error
	description = cur_request['description']
	points = float(cur_request['points'])
	due_date = cur_request['due_date']
	database['items'][number] = {'description': description, 'points' : points, 'status': 'available', 'due_date': due_date, 'owner': ''}
	print('creating item: {0}'.format(number))
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
	new_status = cur_request['new_status']
	if item['status'] == 'available' and new_status in allowed_item_statuses:
		item['status'] = new_status
	else:
		return item_status_error
	new_owner = cur_request['new_owner']
	# we can only assign an owner if there isn't one already, and if the new one is a valid user
	if item['owner'] == '' and new_owner in list(database['users'].keys()):
		item['owner'] = new_owner
	else:
		return item_owner_error
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

def hashify(password):
	"""
	Creates and returns a hex md5 hash of the password (or any string) that comes in
	"""
	hashie = hashlib.md5()
	hashie.update(password.encode('utf-8'))
	pass_hash = hashie.hexdigest()
	return pass_hash

if __name__ == "__main__":
	load_database()
	print('PID: {0}'.format(os.getpid()))
	app.run(debug=True, port=5000, host="0.0.0.0")