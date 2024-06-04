
___

## Оглавление

1. [Tickets](#tickets)
2. [ClientTickets](#clientTickets)
3. [Auth & registration](#auth--registration)
4. [UserData](#userData)
5. [Image](#image)

___

## Tickets

### `POST` [/tickets](http://localhost:8080/tickets)

Создать тикет от лица менеджера

Требования: Роль "менеджер"

Тело запроса

- `typeOfTechnic` - Тип техники
- `model` - модель
- `description` - описание
- `client` - id клиента
- `master` - id мастер
- `idStatus` - id статуса заявки (1 -Новый, 2 -В процессе, 3 -Готово)
          
Тело ответа

- `CREATED`

### `GET` [/tickets](http://localhost:8080/tickets)

Получить список всех тикетов

Требования: Роль "менеджер"

Тело ответа
```json
{
        "id": 
        "typeOfTechnic": 
        "model": 
        "description":
        "client": {
            "id":
            "snils":
            "inn": 
            "passportSeries": 
            "passportNumber": 
            "phone":
            "firstName":
            "lastName": 
            "middleName": 
            "birthdate": 
            "address": 
        },
        "master": {
            "id":
            "snils": 
            "inn": 
            "passportSeries": 
            "passportNumber": 
            "phone":
            "firstName": 
            "lastName": 
            "middleName": 
            "birthdate": 
            "address": 
        },
        "idstatus": {
            "id":
            "statusname":
        },
        "registrationdate":
    }
```
### `GET` [/ticket/{id}](http://localhost:8080/ticket/{id})

Получить тикет по id

Требования: Роль "менеджер"

Тело ответа

```json
{
        "id": 
        "typeOfTechnic": 
        "model": 
        "description":
        "client": {
            "id":
            "snils":
            "inn": 
            "passportSeries": 
            "passportNumber": 
            "phone":
            "firstName":
            "lastName": 
            "middleName": 
            "birthdate": 
            "address": 
        },
        "master": {
            "id":
            "snils": 
            "inn": 
            "passportSeries": 
            "passportNumber": 
            "phone":
            "firstName": 
            "lastName": 
            "middleName": 
            "birthdate": 
            "address": 
        },
        "idstatus": {
            "id":
            "statusname":
        },
        "registrationdate":
    }
```

### `PUT` [/tickets/{id}](http://localhost:8080/tickets/{id})

Обновить тикет по id

Тело запроса

- `typeOfTechnic` - Тип техники
- `model` - модель
- `description` - описание
- `client` - id клиента
- `master` - id мастер
- `idStatus` - id статуса заявки (1 -Новый, 2 -В процессе, 3 -Готово)

___

## ClientTickets

### `POST` [/client-ticket](http://localhost:8080/client-ticket)

Создать тикет от лица клиента

Требования: Роль "клиент"

Тело запроса

- `typeOfTechnic` - Тип техники
- `model` - модель
- `description` - описание
          
Тело ответа

- `CREATED`

### `PUT` [/client-ticket](http://localhost:8080/client-ticket)

Обновить тикет от лица клиента

Требования: Роль "клиент"

Тело запроса

- `typeOfTechnic` - Тип техники
- `model` - модель
- `description` - описание
          
Тело ответа

- `CREATED`

### `GET` [/client-ticket](http://localhost:8080/client-ticket)

Получить все тикеты от лица клиента

Требования: Роль "клиент"

Тело ответа

```json
{
        "id": 
        "typeOfTechnic": 
        "model": 
        "description":
        "clientData": {
            "id": 
            "snils": 
            "inn": 
            "passportSeries":
            "passportNumber": 
            "phone": 
            "firstName": 
            "lastName":
            "middleName": 
            "birthdate": 
            "address": 
        },
        "registrationdate":
    }
```
___

## Auth & registration

### `POST` [/auth/register](http://localhost:8080/auth/register)

Регистрирует пользователя в системе

Тело запроса

- `email`: адрес электронной почты пользователя
- `password`: пароль

Тело ответа

- `token`: токен аутентификации

### `POST` [/auth/authenticate](http://localhost:8080/auth/authenticate)

Авторизует пользователя в системе

Тело запроса

- `email`: адрес электронной почты пользователя
- `password`: пароль

Тело ответа

- `token`: токен аутентификации

___

## UserData

### `PUT` [/user-data](http://localhost:8080/user-data)

Обновить доп.информацию о своём аккаунте (доступно всем)

Тело запроса
```json
{
    "snils":
    "inn":
    "passportSeries":
    "passportNumber":
    "phone":
    "firstName":
    "lastName":
    "middleName":
    "birthdate":
    "address":
}
```

### `GET` [/user-data](http://localhost:8080/user-data)

Получить доп информацию о себе

Тело ответа
```json
{
    "snils":
    "inn":
    "passportSeries":
    "passportNumber":
    "phone":
    "firstName":
    "lastName":
    "middleName":
    "birthdate":
    "address":
}
```
### `GET` [/user-data/{id}](http://localhost:8080/user-data/{id})

Получить доп информацию о лице

Требования: роль "менеджер"

Тело ответа
```json
{
    "snils":
    "inn":
    "passportSeries":
    "passportNumber":
    "phone":
    "firstName":
    "lastName":
    "middleName":
    "birthdate":
    "address":
}
```

___

## Image

### `POST` [/image](http://localhost:8080/image)

Добавить изображение аккаунта

Запрос должен иметь тип содержимого multipart/form-data

Тело запроса

| Key  | Value             |
|------|-------------------|
| file | path-to-photo.jpg |

Тело ответа

- `CREATED`

### `GET` [/image](http://localhost:8080/image)

Получить изображение аккаунта 

Тело ответа

- `*IMAGE*`
