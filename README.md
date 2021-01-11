# elasticsearch-json
通过简单的json获取elasticsearch复杂的检索/聚合DSL

{
  "query": [
    {
      "module": "USER",
      "query": [
        {
          "key": "name",
          "data": [
            "张*三",
            "李*"
          ]
        },
        {
          "key": "age",
          "lt": 20
        }
      ]
    },
    {
      "module": "ORDER",
      "query": [
        {
          "key": "id",
          "data": [
            "0001",
            "0002"
          ]
        }
      ]
    }
  ],
  "dsl": "{\"bool\": {\"filter\": [{\"terms\": {\"phone\": [\"1300000000\"] } } ] } }"
}
