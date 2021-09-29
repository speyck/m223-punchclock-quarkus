<div align="center">
  <h1>
    <img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/zli-logo.png" width="75px"/>
    M223 - Planing
  </h1>
  This document is the main plan for upcoming changes and contains all necesarry information and diagrams stated in the assignment.
</div>

## Use-Case Diagram
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/usecase-diagram/usecase-diagram.png" width="800px" />

### Login  
When the user logs in, the application recieves a `JSON Web Token` (JWT) which it then uses for autorisation on each `REST` request.

### Manage Locations, Categories, Entries  
These cases all inherit the [Manage Entity](#manage-entity) case. The case is listed below and all `extending` / `including` cases will also be inherited.

### Manage Entity  
The Manage Entity contains the main cases for inheriting cases. It's extended by the following cases:

-  **Show**: Returns all avaliable entities
-  **Create**: Creates a new entity
-  **Edit**: Edits/Updates the entity
-  **Delete**: Deletes the entity

### Non-functional requirements
- **Security**: Entities should only be able to be created with a `JWT` token that has the correct autorisation.
- **Data Consistency**: The API should only accept correct and possible data in the correct formats. If the provided data is faulty or incomplete a `401 Bad Request` error should be sent back as response.
- **Performance**: The system should be able to handle multiple requests at a time, so more than one user can use the software simultaneously.
- **Easy-to-use**: The `REST` API should not be overcomplicated and only contain the neccessary interfaces. Difference requests for similar things should be accessible from the same URL. The request URL's should all follow the same style (*naming-convention*).

## Activity Diagram
### Entity Deletion Request
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/activity-diagram/Activitydiagram.png" width="800px" />

## Subject Class Diagram
<sub>'Subject class diagram' is a literal translation of the german name 'Fachklassendiagramm'. The difference to a normal class diagram (to my knowledge) is that only model classes are illustrated.</sub>

<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/fach-class-diagram/class-diagram.png" width="800px" />

## Persona(s)
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/personas/Main Persona.png" width="800px" />

## Package Diagram
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/package-diagram/Packagediagram.png" width="600px" />

## Deployment Diagram
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/deployment-diagram/Deployment-diagram.png" />

## Class Diagram
<img src="https://github.com/speyck/m223-punchclock-quarkus/blob/main/docs/class-diagram/Classdiagram.png" />
