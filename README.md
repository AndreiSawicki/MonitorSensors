# MonitorSensors
## Overview
Sample application based on [ОЦР_ТЗ](./documentation/ОЦР_ТЗ.pdf)

### build: 
`gradlew clean build`

### via docker:
`docker-compose up`

will be available:
app
- http://localhost:8181/swagger-ui/index.html#/

- http://localhost:8181/auth/sign-in
- http://localhost:8181/api/v1/sensor

DB
- postgresql://localhost:5432/monitorSensors_DB
monitorServiceUser/monitorServiceDBpass

pgadmin4
- http://localhost:5050/
Credentials: adminPG@admin.com/adminPG_pass

## Demo data
### users
| username  |  password | role    |
|-----------|-----------|---------|
| admin     | admin     | ADMIN   |
| user      | user      | VIEWER  |

### units of measure
| id          | unit_name |
|-------------|-----------|
| bar         | Bar       |
| voltage     | Voltage   |
| temperature | °C        |
| humidity    | %         |

### sensor types
| id          | type_name   |
|-------------|-------------|
| pressure    | Pressure    |
| voltage     | Voltage     |
| temperature | Temperature |
| humidity    | Humidity    |

### sensors
| name    | model   | type_id  | range_from | range_to | unit_of_measure_id | location | description              |
|---------|---------|----------|------------|----------|--------------------|----------|--------------------------|
| Sensor1 | model-1 | pressure | 5          | 50       | bar                | room1    | sensor at room 1         |
| Sensor2 | model-2 | voltage  | 150        | 250      | voltage            | room1    | Another sensor at room 1 |




