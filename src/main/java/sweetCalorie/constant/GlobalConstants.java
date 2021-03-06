package sweetCalorie.constant;

public class GlobalConstants {

    public static final String FOODS_FILE_PATH = "src/main/resources/files/json/food.json";
    public static final String RECIPES_FILE_PATH = "src/main/resources/files/json/recipe.json";


    public static final String USERNAME_NEEDED = "Потребителското име е задължително!";
    public static final String USERNAME_LENGTH = "Потребителското име трябва да е с дължина от 5 до 20 символа!";
    public static final String USERNAME_SYMBOLS = "Потребителското име трябва да съдържа единствено букви, цифри и _!";
    public static final String USERNAME_PASSWORD = "Грешно потреботелско име или парола!";
    public static final String USERNAME_NOT_FOUND = "Няма регистриран потребител с такова потребителско име";

    public static final String EMAIL_NEEDED = "Емайл адресът е задължителен!";
    public static final String VALID_EMAIL = "Моля въведете валиден емайл адрес!";
    public static final String EMAIL_IN_DB = "Вече има регистриран потребител с този емайл адрес.";

    public static final String PASSWORD_NEEDED = "Паролата е задължителна!";
    public static final String PASSWORD_LENGTH = "Паролата трябва да е между 8 и 20 символа!";
    public static final String PASSWORD_EQUALITY = "Паролите трябва да съвпадат!";

    public static final String COMMENT_LENGTH = "Коментарът трябва да е с дължина от 2 до 500 символа!";
    public static final String FIELD_NEEDED = "Това поле е задължително.";
    public static final String FIELD_LENGTH = "Наименованието трябва да е с дължина от 2 до 20 символа.";
    public static final String FIELD_PATTERN = "Полето трябва да съдържа само букви.";
    public static final String AUTOMATION_EMAIL = "Това е автоматичен емаил, съдържащ вашето запитване";
    public static final String EMAIL_REQUEST = "Въпрос от потребител ";
    public static final String EMAIL_LENGTH = "Въпросът трябва да е от поне 10 символа. ";
    public static final String POSITIVE_NUMBER = " трябва да е позитивно число.";

    public static final String WEIGHT_OBESE = "Препоръчваме ви да се консултирате с лекар.Това тегло води до други здравеословни проблеми. Важно е да свалите голяма част от него, но няма причина за депресия. Щом сте посетили нашия сайт имате желание да подобрите здравословноти си състояние, а това е всичко от което се нуждаете! Желание и воля.";
    public static final String WEIGHT_OVER = "Вашето тегло е леко завишено, но бързо можем да ви вкараме в нормите. Няма нужда да се подлагате на гладувания и лишения. Следвайте нашите съвете, направете малки промени в начина си на хранене и резултатите няма да закъснеят.";
    public static final String WEIGHT_IDEAL = "Вашето тегло е в деалните граници. Продължавайте да се храните здравословно и да спортувате, за да го поддържате и за напред така. В нашия сайт ще намерите полезни савети и рецепти с които да поддържате добрата си форма.";
    public static final String WEIGHT_UNDER = "Вашето тегло е под препоръчителните норми. Опитайте се да повишите калорийния си прием и добавете силови тренировки 1-2 пъти седмично.";
    public static final String WEIGHT_BOUNDARIES = "Индексът на телесна маса показва цялостното ви телесно състояние. Здравословното тегло влиза в границите от 18,5 до 25 кг/м^2. На вашата виисочина от ";
    public static final String BMI_DESCRIPTION = "Индексът на телесната маса (съкратено ИТМ, на английски: body mass index, (BMI) e медико-биологичен показател, който служи за определяне на нормалното, здравословно тегло при хора с различен ръст и за диагностициране на затлъстяване и недохранване. Индексът на телесната маса се измерва в килограми на квадратен метър и се определя в зависимост от килограмите и височината на човека.";

    public static final String TITLE_NEEDED = "Необходимо е да се въведе наименование на рецептата!";
    public static final String TITLE_LENGTH = "Минималната дължина е 3 символа!";

    public static final String DESCRIPTION_NEEDED = "Описанието на рецептата е задължително!";
    public static final String INGREDIENTS_NEEDED = "Необходимо е да се въведе поне една съставка";

    public static final String FOOD_IN_DB = "Храна с посоченото име вече съществува в базата данни.";
}