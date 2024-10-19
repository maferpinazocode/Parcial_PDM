# Parcial_PDM - Programación Dispositivos Moviles
Este repositorio contiene el proyecto desarrollado para el examen parcial, enfocado en la creación de un juego interactivo de preguntas y respuestas. Todo el proyecto está realizado en **Kotlin**. 

## Estructura
- **Parcial_PDM/**: Solución al ejercicio.
- **Rubrica/**: Rúbrica de evaluación.
- **README.md**: Información sobre el proyecto.

## Instrucciones para ejecutar el proyecto

1. **Clonar** el repositorio:
   ```bash
   git clone https://github.com/maferpinazocode/Parcial_PDM.git
   
2. **Acceder** a la carpeta que contiene los ejercicios:
    ```bash
    cd Parcial_PDM/AppParcial/
    ```
3. **Abrir el proyecto** con Android Studio u otro.
4. En el IDE, **navegar** a la estructura de archivos:
    ```
    src/main/kotlin/
    ```
5. **Ejecutar** en el emulador o dispositivo conectado la aplicación.
6. **Revisar los resultados** en la consola.
   
## Estructura Detallada del Repositorio

```plaintext
AppParcial/
├── .gitignore
├── README.md
└── QuestionsAnswers/
    ├── app/
    │   ├── manifests/
    │   │   └── AndroidManifest.xml
    │   ├── kotlin/
    │   │   └── com.example.questionsanswers/
    │   │       ├── MainActivity.kt
    │   │       ├── AnswerFragment.kt
    │   │       ├── QuestionFragment.kt
    │   │       ├── GResultFragment.kt
    │   │       └── WelcomeFragment.kt
    │   ├── java/
    │   │   └── com.example.questionsanswers/
    │   ├── res/
    │   │   ├── drawable/
    │   │   ├── layout/
    │   │   │   ├── activity_main.xml
    │   │   │   ├── fragment_answer.xml
    │   │   │   ├── fragment_question.xml
    │   │   │   ├── fragment_result.xml
    │   │   │   └── fragment_welcome.xml
    │   │   ├── navigation/
    │   │   │   └── nav_graph.xml
    │   │   └── values/
    │   │       ├── colors.xml
    │   │       ├── strings.xml
    │   │       └── themes.xml
    │   └── androidTest/
    │       └── com.example.questionsanswers/
    ├── test/
    │   └── com.example.questionsanswers/
    ├── build.gradle.kts (Project: QuestionsAnswers)
    └── build.gradle.kts (Module: app)
