@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <h1>Список пользователей</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Email</th>
            <th>Действие</th>
        </tr>
        </thead>
        <tbody>
        @for (var user : page.getUsers())
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()} ${user.getPassword()}</td>
                <td>${user.getEmail()}</td>
                <td><a href="/users/${user.getId()}">Просмотр</a></td>
            </tr>
        @endfor
        </tbody>
    </table>
`
)
