# OptimalPacking

### Получение списка всех посылок:
-l 

### Получение посылки по имени:
-g Штанга

### Редактирование посылки
-e Штанга -w 5 -h 5 -n Штанга150

### Удаление посылки по имени
-d Штанга

### Импорт посылок Алгоритм максимальной плотности 

-i -file src/main/resources/packages.txt -maxTrucks 10 -packingAlgorithm DensePacking

### Импорт посылок Алгоритм равномерной загрузки
-i -file src/main/resources/packages.txt -maxTrucks 10 -packingAlgorithm UniformPacking