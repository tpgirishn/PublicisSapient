### Move to correct directory path ###
    cd book-my-show (from the downloaded directory path as root)


### Set Up Local DB environment ###
    ./gradlew flywayMigrate


### Build source jar ###
    ./gradlew bootRun


### To onboard showtimes and theater details by theater owners ###

Step 1 - Create:
    
`curl --location --request POST 'localhost:8080/showtimes' \
--header 'Content-Type: application/json' \
--data '    {
"endTime": "2025-12-19T17:47:33.928988Z",
"format": "IMAX",
"id": "sh2",
"language": "English",
"movie": {
"description": "Space exploration epic",
"durationMinutes": 169,
"genre": "Sci-Fi",
"id": "m2",
"language": "English",
"rating": "PG-13",
"releaseDate": "2014-11-07",
"title": "Interstellar"
},
"movie_id": "m2",
"screen": {
"id": "s442",
"name": "Screen 2",
"seats": [{
"screenId": "s442",
"id": "seat101",
"seatNumber": "B101",
"seatType": "REGULAR",
"vip": false,
"status": "AVAILABLE",
"price": 100
},{
"screenId": "s442",
"id": "seat102",
"seatNumber": "B109",
"seatType": "REGULAR",
"vip": false,
"status": "AVAILABLE",
"price": 100
},{
"screenId": "s442",
"id": "seat103",
"seatNumber": "B103",
"seatType": "REGULAR",
"vip": false,
"status": "AVAILABLE",
"price": 100
}],
"theater": {
"address": "MG Road",
"city": "Bangalore",
"id": "t1",
"name": "PVR Cinemas",
"phone": "0801234567",
"state": "Karnataka",
"zipCode": "560001"
},
"totalSeats": 80
},
"startTime": "2025-12-21T12:47:33.928988Z"
}'`

### Verify in cache ####
`curl --location --request GET 'localhost:8080/cache' \
--header 'Content-Type: application/json'`

### To update and delete modify the above payload of POSTand change HTTP verbs in curl request accordingly..post which verification can be done in cache ###


### To book a ticket ###
`curl --location 'localhost:8080/ticket' \
--header 'Content-Type: application/json' \
--data '{
"bookingSeats": [
{
"id": "seat4",
"price": 100.00,
"screenId": "s2",
"seatNumber": "A1",
"seatType": "REGULAR",
"status": "AVAILABLE",
"isVip": false
},
{
"id": "seat4",
"price": 100.00,
"screenId": "s2",
"seatNumber": "A1",
"seatType": "REGULAR",
"status": "AVAILABLE",
"isVip": false
},
{
"id": "seat4",
"price": 100.00,
"screenId": "s2",
"seatNumber": "A1",
"seatType": "REGULAR",
"status": "AVAILABLE",
"isVip": false
}
],
"bookingTime": "2025-12-18T16:28:07.495257Z",
"showtime": {
"id": "sh1",
"movie": {
"id": "m2",
"title": "Inception"
},
"screen": {
"id": "s2",
"theater": {
"id": "t1"
}
},
"startTime": "2025-12-19T16:28:07.495257Z"
},
"user": {
"id": "u1"
}
}'`