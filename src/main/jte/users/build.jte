@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.BuildUserPage;
@param BuildUserPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание пользователя</title>
</head>
<body>
<h1>Создание пользователя</h1>

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif
<%--Тут именованный маршрут   -   "/users"--%>
<form action="${NamedRoutes.usersPath()}" method="post">
    <div>
        <label>
            Имя
            <input type="text" name="name" value="${page.getName()}" />
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="email" required name="email" value="${page.getEmail()}" />
        </label>
    </div>
    <div>
        <label>
            Пароль
            <input type="password" required name="password" />
        </label>
    </div>
    <div>
        <label>
            Подтверждение пароля
            <input type="password" required name="passwordConfirmation" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>
</body>
</html>
