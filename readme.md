<h1>Planet API</h1>

How to build project: `` gradle build ``

How to run project:
```
docker-compose up -d
gradle bootRun
```

The project will be running on **localhost:8080**

Functionalities:

**Create planet:**
```
POST /api/v1/planet

{
    "name": "Tatooine",
    "terrain": "planet-Terrain",
    "climate": "planet-climate"
}
```

**Get planet by id:**
```
GET /api/v1/planet/{planetId}
```

**List planets filtering by name (name is optional):**
```
GET /api/v1/planet?name={name}
```

**Delete planet:**
```
DELETE /api/v1/planet/{planetId}
```