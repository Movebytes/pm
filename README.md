# Project Management Test Application
Java EE Application
## Stack / Technologies / Instruments
- JPA
- JAX-RS
- CDI/EJB3
- Gradle
- H2
- liquibase

## Dependencies
- group 'javax.enterprise', name: 'cdi-api', version: '2.0'
- group 'org.jboss.spec.javax.ejb', name: 'jboss-ejb-api_3.2_spec', version: '1.0.1.Final'
- group 'org.hibernate', name: 'hibernate-core', version: '5.3.6.Final'
- group 'com.google.auto.service', name: 'auto-service', version: '1.0-rc4'
- group 'javax.validation', name: 'validation-api', version: '2.0.1.Final'
- group 'org.json', name: 'json', version: '20180813'
- group 'org.liquibase', name: 'liquibase-core', version: '3.6.2'
- group 'com.mattbertolini', name: 'liquibase-slf4j', version: '2.0.0'
- group 'org.jboss.spec.javax.annotation', name: 'jboss-annotations-api_1.2_spec', version: '1.0.2.Final'
- group 'org.jboss.spec.javax.ws.rs', name: 'jboss-jaxrs-api_2.0_spec', version: '1.0.1.Final'
- group 'junit', name: 'junit', version: '4.11'

## Implemented features
- liquibase migration
- User entity, model, service, rest service (CRUD)
- Project entity, model, service, rest service (CRUD)
- Assigning active user to active project
- Validation of models

## TODO Features
- Create reports
- Package into Docker container
- Tests (Unit)
- UI
