#!/bin/sh

DB_PASSWORD=$(cat /run/secrets/db_password)
export DB_PASSWORD


exec "$@"