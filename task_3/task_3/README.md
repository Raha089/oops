# Electronic Voting System

Это проект системы электронного голосования с использованием PostgreSQL для хранения данных.

## Установка

### 1. Клонирование репозитория

Сначала клонируйте репозиторий на свой локальный компьютер:
git clone https://github.com/Ckareb/Object-oriented-programming-systems

### 2. Восстановление БД из dump
используйте эти команды в командной строке (при включенном postgres желательно иметь 17 версию)
set PGPASSWORD=postgres; psql -U postgres -c "CREATE DATABASE electronic_voting_system;"
set PGPASSWORD=postgres; psql -U username -d electronic_voting_system -f "electronic_voting_system.sql"
место восстановления можно определить самостоятельно в данной команде

# Можно пользоваться)))

