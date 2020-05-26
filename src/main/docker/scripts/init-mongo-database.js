db.createCollection( "todo_list", {
    validator: { $jsonSchema: {
            bsonType: "object",
            required: [ "title", "priority", "description"],
            properties: {
                title: {
                    bsonType : "string"
                },
                priority: {
                    bsonType : "int"
                },
                description: {
                    bsonType : "string"
                }
            }
        } }
} )
