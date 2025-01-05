from flask import Flask, jsonify, send_from_directory
import json
import os

app = Flask(__name__, static_folder='assets')  # 기본 정적 파일 디렉토리: assets

# 현재 app.py가 위치한 디렉토리를 기준으로 설정
BASE_DIR = os.path.dirname(os.path.abspath(__file__))



# 루트 URL로 index.html 반환
@app.route('/')
def index():
    
    return send_from_directory(BASE_DIR, 'index.html')


@app.route('/assets/templates/state.json')
def get_state_json():
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), 'state.json')

@app.route('/assets/templates/nav.html')
def get_nav_html():
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), 'nav.html')



# 정적 파일 제공 (CSS, JS, 이미지 등)
@app.route('/assets/<path:path>')
def assets_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'assets'), path)

# Pages 디렉토리 정적 파일 제공
@app.route('/pages/<path:path>')
def pages_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'pages'), path)

# Templates 디렉토리 정적 파일 제공 (footer.html 등)
@app.route('/assets/templates/<path:path>')
def templates_files(path):
    return send_from_directory(os.path.join(BASE_DIR, 'assets/templates'), path)

# API to fetch user data
@app.route('/api/getUserData', methods=['GET'])
def get_user_data():
    try:
        json_path = os.path.join(BASE_DIR, 'assets/templates/userData.json')
        with open(json_path, 'r', encoding='utf-8') as file:
            user_data = json.load(file)
        return jsonify(user_data), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5001)  # 5001번 포트로 실행
