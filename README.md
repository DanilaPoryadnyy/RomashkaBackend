# RomashkaCompany API

___

## Оглавление

1. [Product](#product)

___

## Product

### `POST` [/product](http://localhost:8080/product)

Создать товар

Тело запроса

- `name` - Название товара (ограничено 255 символами и обязательно при создании)
- `description` - Описание товара (ограничено 4096 символами)
- `price` - Цена товара (Не меньше "0")
- `available` - Наличие товара (по умол. false)

### `GET` [/product](http://localhost:8080/product)

Получить список товаров

Тело ответа

- `id`
    - `name` - Название товара
    - `description` - Описание товара
    - `price` - Цена товара
    - `available` - Наличие товара

### `GET` [/product/{id}](http://localhost:8080/product/{id})

Получить товар по id

Тело ответа

- `id`
- `name` - Название товара
- `description` - Описание товара
- `price` - Цена товара
- `available` - Наличие товара

### `PUT` [/product/{id}](http://localhost:8080/product/{id})

Обновить товар по id

Тело запроса

- `name` - Название товара (ограничено 255 символами и обязательно при создании)
- `description` - Описание товара (ограничено 4096 символами)
- `price` - Цена товара (Не меньше "0")
- `available` - Наличие товара (по умол. false)

### `DELETE` [/product/{id}](http://localhost:8080/product/{id})

Удалить товар по id