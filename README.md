# Команда IOI - АвиаХакатон 2022, Кейс Шеремтьево
# Управление пассажирскими автобусами в аэропорту
## Техническое задание

Необходимо разработать систему управления пассажирскими автобусами аэропорту.

Кто пользователи?
* Водители автобусов
* Диспетчеры аэропорта
* Руководители аэропорта

Задачи:
* Формирование заданий водителям
* Контролировать исполнение задач диспетчером
* Визуализировать процесс исполнения в режиме реального времени (не в виде карты)
* Интерфейс для водителя
* Оптимизация количества используемой техники и водителей

В веб-интерфейсе решения диспетчер должен иметь возможность:
* Просмотреть задачи, назначенные ресурсам
* Изменить исполнителя, продолжительность и время задачи (интерактивно)
* Изменить масштаб отображения задач
* Визуально понять, в каком статусе находится
* Посмотреть детальную информацию по задаче

В веб-интерфейсе мобильного приложения должен быть функционал:
* Базовой авторизации
* Принять, начать, закончить выбранную задачу
* Завершенные задачи в интерфейсе исполнителя не отображаются
* Панель статистики в интерфейсе диспетчера: Up to you ))

По результатам разработки необходимо предоставить:
* Презентацию, отражающую особенности решения
* Автоматический генератор задач на доставку пассажиров автобусами
* Автоматический оптимизатор задач (назначение исполнителей)
* Веб-интерфейс рабочего места диспетчера
* Веб-интерфейс рабочего места исполнителя (на мобильном телефоне)
* Исходный код, размещенный в открытых источниках

При реализации требуется учесть, что:
* Занятый ресурс не может быть использован в одно и то же время на разных задачах
* Диспетчер может назначать и снимать задачи с исполнителя
* Задачи без исполнителя должны отображаться в отдельной зоне
* С системой может работать несколько диспетчеров
* Требуется оперативное отображение изменений по задачам
