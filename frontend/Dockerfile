# 베이스 이미지 설정
FROM python:3.9-slim

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 소스 복사
COPY . /app

# Python 종속성 설치
RUN pip install --no-cache-dir flask

# 환경 변수 설정
ENV FLASK_APP=app.py
ENV FLASK_RUN_PORT=9027
ENV FLASK_RUN_HOST=0.0.0.0

# Flask 앱 실행 환경
EXPOSE ${PORT}

# ENTRYPOINT 실행
CMD ["flask", "run", "--port=9027"]
