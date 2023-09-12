
>Using the PostgreSQL db as an docker image
> To bring up execute following commands
> 
>> docker compose up -d
> 
> To execute above need a configuration file, which is docker-compose.yml under resources.
> 
> once its up you can check logs
> 
>> docker logs postgres -f
> 
> The postgres DB configuration has been defined in docker-compose.yaml
> 
> 
> After this configure the DB datasource details in application.yml
> if trying to start the application it will throw an error as customer database does not exist
> 
> to create the database follow as -
> > docker exec -it postgres bash
> 
> > psql -U admin
> 
> > \l
> 
> > create database customer;
> 
> >\c customer
> 
> this will connect the customer database.
> Apply the JPA changes in pom.xml & Entity class & restart - that will create the table customer
> 
> >select * from customer;
> 
> Add Repository & expose get end point to get all customers
> this can be validated using the Postman tool
> 
> http://localhost:8080/api/customers/all
> 
> 