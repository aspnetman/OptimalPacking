# OptimalPacking

### Получение списка всех посылок:

list

### Получение посылки по имени:

get --name Штанга

### Редактирование посылки

edit --name Штанга --width 5 -height 5 -newname Штанга150

### Удаление посылки по имени

delete --name Штанга

### Импорт посылок Алгоритм максимальной плотности

import --userId 8 --file src/main/resources/packages.txt --maxTrucks 10 --packingAlgorithm DensePacking

### Импорт посылок Алгоритм равномерной загрузки

import --userId 8 --file src/main/resources/packages.txt --maxTrucks 10 --packingAlgorithm UniformPacking

### Разгрузка грузовиков

export --userId 8 --trucksFile src/main/resources/trucks.txt

### Получить детали по счёту

get-billing-detail -u 8 -f 2024-01-01 -t 2025-02-02