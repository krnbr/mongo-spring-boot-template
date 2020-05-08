Step 1 - docker exec -it localmongo_docker mongo

Step 2 :-
    
rs.initiate(
   {
     _id : 'rs0',
     members: [
       { _id : 0, host : "mongo:27017" }
     ]
   }
 )
 
Step 3 - exit



