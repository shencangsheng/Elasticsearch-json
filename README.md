# elasticsearch-json
elasticsearch，通过简单的json获取复杂的检索/聚合DSL

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
  "dsl": "{\"bool\": {\"filter\": [{\"terms\": {\"user.phone\": [\"1300000000\"] } } ] } }"
}