# Activité Pratique Spring MVC Thymeleaf

Voir la vidéo :Créer une application Web JEE basée sur Spring MVC, Thylemeaf et Spring Data JPA qui permet de gérer les patients. L'application doit permettre les fonctionnalités suivantes :
- Afficher les patients
- Faire la pagination
- Chercher les patients
- Supprimer un patient




### Entities:
package : (`src/main/java/com/example/apppatient/entities`)

    -   Patient:
![img.png](images/img.png)

### Repositories:
package : (`src/main/java/com/example/apppatient/repositories`)

    - PatientRepository :
![img1.png](images/img_1.png)

### Security :
package : (`src/main/java/com/example/apppatient/security`)

    - SecurityConfig :
![img2.png](images/img_2.png)
![img3.png](images/img_3.png)


### Templates :
package : (`src/main/resources/templates`)

    - mainTemplate.html :
![img4.png](images/img_4.png)

    - home.html :
![img5.png](images/img_5.png)

    - patients.html :
![img6.png](images/img_6.png)

    - formPatients.html :
    - editPatient.html :
![img7.png](images/img_7.png)

### Web :
package : (`src/main/java/com/example/apppatient/web`)


    - PatientController :
![img8.png](images/img_8.png)
![img9.png](images/img_9.png)
![img10.png](images/img_10.png)

    - SecurityController :
![img11.png](images/img_11.png)


### application.properties :

![img13.png](images/img_13.png)

### AppPatientApplication :

![img12.png](images/img_12.png)












