from flask import Flask, jsonify, send_from_directory
import json
import os

app = Flask(__name__, static_folder='assets')

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

@app.route('/')
def index():
    return send_from_directory(BASE_DIR, 'index.html')

@app.route('/assets/templates/state.json')
def get_state_json():
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), 'state.json')

@app.route('/assets/templates/nav.html')
def get_nav_html():
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), 'nav.html')

@app.route('/assets/<path:path>')
def assets_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'assets'), path)

@app.route('/pages/<path:path>')
def pages_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'pages'), path)

@app.route('/assets/templates/<path:path>')
def templates_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), path)


if __name__ == '__main__':
    app.run(debug=True, port=5000)
