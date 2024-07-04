@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.model.User
@param User user

@template.layout.page(
content = @`
    <h1>Редактирование пользователя</h1>
    <form action="${NamedRoutes.userPath(user.getId())}" method="post">
        <input type="hidden" name="_method" value="patch">
        <div class="mb-3">
            <label for="name">Имя:</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.getName()}">
        </div>
        <div class="mb-3">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.getEmail()}">
        </div>
        <div class="mb-3">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" value="${user.getPassword()}">
        </div>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
`
)
