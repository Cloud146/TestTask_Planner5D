# Task: Planner 5D Editor Test Automation

Write UI and API tests that verify that clicking sofa item in Planner 5D editor works correctly.

## What you need to do:
1. Write UI test which would check that clicking on any sofa in Planner 5D editor catalog adds the sofa item to the project.
2. Write UI test which would test that the added sofa is visible in Planner 5D editor 3D mode.
3. Write API test which would test that calling the get project data/json API after 10 seconds will return correct project data/json containing the sofa.
4. If you have some more good ideas what should be tested feel free to add those also.

## Requirements
- Use Kotlin (or Java if you don't know Kotlin) for writing tests.
- Use at least once Kotlin coroutines (if writing in Kotlin) or virtual threads (if writing in Java).
- Provide the task results as a git repository packaged in a gitbundle and do not post it on public code sharing services (github, bitbucket, ...) - include the tests and the iOS app.
- We expect to be able to run your code without fuss - so if there are any specifics that are needed to get your code running, please commit them to git repository if possible.

## What we will look at
- Clarity, elegance and maintainability of code.
- Code consistency.

---

# Описание задачи (Russian Translation)

Задача: написать UI и API тесты, проверяющие корректность добавления дивана по клику в редакторе Planner 5D.

## Что нужно сделать:
1. Написать UI тест, который проверит, что клик по любому дивану в каталоге редактора Planner 5D добавляет этот диван в проект.
2. Написать UI тест, который проверит, что добавленный диван отображается в 3D-режиме редактора Planner 5D.
3. Написать API тест, который проверит, что вызов API получения данных/json проекта через 10 секунд возвращает корректные данные/json проекта, содержащие добавленный диван.
4. Если у вас есть другие хорошие идеи о том, что еще стоит проверить — смело добавляйте их.

## Требования:
- Использовать Kotlin (или Java, если вы не знаете Kotlin) для написания тестов.
- Использовать как минимум один раз корутины (при написании на Kotlin) или виртуальные потоки / Virtual Threads (при написании на Java).
- Предоставить результаты выполнения в виде git-репозитория, упакованного в файл `gitbundle`. **Не публикуйте** код на открытых сервисах (GitHub, Bitbucket и т.д.). *Примечание: в оригинале сказано "включите тесты и iOS приложение" (возможно, опечатка в их шаблоне, но имейте в виду).*
- Мы ожидаем, что ваш код запустится без лишних проблем («танцев с бубном»). Поэтому, если для запуска нужны какие-то специфические настройки, по возможности закоммитьте их в git-репозиторий.

## На что мы будем смотреть:
- Чистота, элегантность и поддерживаемость кода.
- Консистентность (единообразие) кода.