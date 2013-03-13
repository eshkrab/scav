#!/usr/bin/env python2.7
"""
@about Flask server for the Scav app
@author Placeholder Studios
"""

from __future__ import print_function
from flask import Flask, request
import json, hashlib, os

app = Flask(__name__)

database = {}
dbfile = "scavdb.json"

access_key = open('acess_key').read()

success_message = json.dumps({"status" : "success"})
illegal_access_key_error = json.dumps({'status': 'error', 'message': 'illegal access key'})
no_team_error = json.dumps({'status': 'error', 'message': 'no such team'})
login_incorrect_error = json.dumps({'status': 'error', 'message': 'login data incorrect'})

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
	cnetid = cur_request["cnetid"]
	pass_hash = hashify(cur_request['password'])
	team = cur_request["team"]
	if(team not in database["teams"]):
		return no_team_error
	database["users"][cnetid] = {"cnetid" :cnetid, "pass_hash": pass_hash, "team" : team}
	database["teams"][team].append(cnetid)
	print('creating user: {0}'.format(cnetid))
	save_database()
	return success_message

@app.route("/createTeam", methods = ['POST'])
def create_team():
	"""
	Needs: access_key, team (name)
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	team = cur_request['team']
	database["teams"][team] = []
	print('creating team: {0}'.format(team))
	save_database()
	return success_message

@app.route("/createItem", methods = ['POST'])
def create_item():
	"""
	Needs: access_key, item_name, number, description, points
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_access_key_error
	number = cur_request['number']
	item_name = cur_request["name"]
	description = cur_request["description"]
	points = cur_request["points"]
	database["items"][number] = {'name': item_name, 'description': description, 'points' : points}
	print('creating item: {0}'.format(item_name))
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
	user = database['users'][cnetid]
	if hashify(password) == user['pass_hash']:
		return json.dumps(user)
	else:
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
def get_team():
	"""
	Needs: access_key, number
	"""
	cur_request = request.form if request.json is None else request.json
	if cur_request['access_key'] != access_key:
		return illegal_acccess_key_error
	number = cur_request['number']
	try:
		team = database['items'][number]
	except KeyError:
		return no_team_error
	return json.dumps(item)

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
