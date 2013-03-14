#!/usr/bin/env python2.7
"""
@about Flask server for the Scav app
@author Placeholder Studios
"""

from __future__ import print_function
from flask import Flask, request
import json, hashlib, os, pprint

app = Flask(__name__)

database = {}
dbfile = "scavdb.json"

access_key = open('acess_key').read()

success_message = json.dumps({"status" : "success"})
illegal_access_key_error = json.dumps({'status': 'error', 'message': 'illegal access key'})
no_team_error = json.dumps({'status': 'error', 'message': 'no such team'})
login_incorrect_error = json.dumps({'status': 'error', 'message': 'login data incorrect'})

no_item_error = json.dumps({'status': 'error', 'message': 'no such item'})
item_status_error = json.dumps({'status': 'error', 'message': 'status must be available, in progress or done'})

no_user_error = json.dumps({'status': 'error', 'message': 'no such user'})

allowed_item_statuses = {'available', 'in progress', 'done'}

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
	Needs: access_key, cnetid, password, team
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	cnetid = cur_request['cnetid']
	pass_hash = hashify(cur_request['password'])
	team = cur_request['team']
	if(team not in database["teams"]):
		return no_team_error
	email = cnetid + '@uchicago.edu'
	database['users'][cnetid] = {'email': email, 'pass_hash': pass_hash, 'team': team}
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
	database["teams"][team] = {'captain': captain, 'members': []}
	print('creating team: {0}'.format(team))
	save_database()
	return success_message

@app.route("/createItem", methods = ['POST'])
def create_item():
	"""
	Needs: access_key, item_name, number, description, points, status, due_date
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	number = cur_request['number']
	description = cur_request['description']
	points = cur_request['points']
	status = cur_request['status']
	if status not in allowed_item_statuses:
		return item_status_error
	due_date = cur_request['due_date']
	database['items'][number] = {'description': description, 'points' : points, 'status': status,'due_date': due_date}
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
		print('no such user')
		print(cur_request)
		return no_user_error
	if hashify(password) == user['pass_hash']:
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

@app.route('/changeItemStatus', methods=['POST'])
def change_item_status():
	"""
	Needs: access_key, number, new_status
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
	if new_status in allowed_item_statuses:
		item['status'] = new_status
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