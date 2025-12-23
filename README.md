### Move to correct directory path ###
    cd book-my-show (from the downloaded directory path as root)


### Set Up Local DB environment ###
    ./gradlew flywayMigrate


### Build source jar ###
    ./gradlew bootRun


### To onboard showtimes and theater details by theater owners ###

Step 1 - Create:
    
`curl --location 'localhost:8080/theater' \
--header 'Content-Type: application/json' \
--data '{
"address": "MG Road",
"city": "Bangalore",
"name": "PVR Cinemas",
"phone": "0801234567",
"state": "Karnataka",
"zipCode": "77665544",
"screens": [
{
"name": "Screen 1",
"seats": [
{
"price": 100.00,
"seatNumber": "A1",
"seatType": "REGULAR",
"status": "AVAILABLE",
"vip": false
},
{
"price": 100.00,
"seatNumber": "A2",
"seatType": "REGULAR",
"status": "AVAILABLE",
"vip": false
}
]
}
]
}'`

### Verify in cache ####
`curl --location --request GET 'localhost:8080/cache' \
--header 'Content-Type: application/json'`

### To update and delete modify the above payload of POST and change HTTP verbs in curl request accordingly..post which verification can be done in cache ###


### To book a ticket ###


`curl --location 'localhost:8080/ticket' \
--header 'Content-Type: application/json' \
--data '{
"bookingSeats": [
            {
                "id": 1,
                "price": 100.00,
                "seatNumber": "A1",
                "seatType": "REGULAR",
                "status": "AVAILABLE",
                "vip": false
            },
            {
                "id": 2,
                "price": 100.00,
                "seatNumber": "A2",
                "seatType": "REGULAR",
                "status": "AVAILABLE",
                "vip": false
            }
        ],
    "bookingTime": "2025-12-18T16:28:07.495257Z",
    "showtime": {
        "id": "213456789",
        "movie": {
            "id": 213456789,
            "title": "Inception"
        },
        "screen": {
            "id": 123456789,
            "theater": {
                "id": "t1"
            }
        },
        "startTime": "2025-12-21T16:28:07.495257Z"
    },
    "user": {
        "id": 123456789
    }
}'`
