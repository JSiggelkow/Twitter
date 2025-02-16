#!/bin/sh

DB_PASSWORD=$(cat /run/secrets/db_password)
export DB_PASSWORD
JWT_KEY=$(cat /run/secrets/jwt_key)
export JWT_KEY


exec "$@"