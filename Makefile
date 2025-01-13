SERVICE_NAME=rebate-service
IMAGE_TAG=$(SERVICE_NAME)-img

default: ci

ci: build
	docker run --rm $(IMAGE_TAG) bash -c 'which java && java -version'
	echo "Success"

build:
	docker build --pull -t $(IMAGE_TAG) .
	echo "Success"

run-setup:
	docker compose up

