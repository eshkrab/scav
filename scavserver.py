#!/usr/bin/env python2.7

from __future__ import print_function
from flask import Flask, request
import json, hashlib, os

app = Flask(__name__)

database = {}
dbfile = "scavdb.json"
successMessage = json.dumps({"status" : "success"})
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
	if request.json is None:
		cnetid = request.form['cnetid']
		pass_hash = hashify(request.form['password'])
		team = request.form['team']
	else:
		cnetid = request.json["cnetid"]
		pass_hash = hashify(request.json['password'])
		team = request.json["team"]
	if(team not in database["teams"]):
		return no_team_error
	database["users"][cnetid] = {"cnetid" :cnetid, "pass_hash": pass_hash, "team" : team}
	database["teams"][team].append(cnetid)
	print('creating user: {0}'.format(cnetid))
	save_database()
	return successMessage

@app.route("/createTeam", methods = ['POST'])
def create_team():
	if request.json is None:
		team = request.form['team']
	else:
		team = request.json['team']
	database["teams"][team] = []
	print('creating team: {0}'.format(team))
	save_database()
	return successMessage

@app.route("/createItem", methods = ['POST'])
def create_item():
	if request.json is None:
		item = request.form['item']
		description = request.form['description']
	else:
		item = request.json["item"]
		description = request.json["description"]
	database["items"][item] = description
	print('creating item: {0}'.format(item))
	save_database()
	return successMessage

@app.route('/getUser', methods=['POST'])
def get_user():
	if request.json is None:
		cnetid = request.form['cnetid']
		password = request.form['password']
	else:
		cnetid = request.json['cnetid']
		password = request.json['password']
	user = database['users'][cnetid]
	if hashify(password) == user['pass_hash']:
		return json.dumps(user)
	else:
		return login_incorrect_error

@app.route('/getTeam', methods=['POST'])
def get_team():
	if request.json is None:
		teamname = request.form['team']
	else:
		teamname = request.json['team']
	try:
		team = database['teams'][teamname]
	except KeyError:
		return no_team_error
	return json.dumps(team)

@app.route("/getAllUsers")
def list_users():
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