@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    <h1>Привет, Хекслет!</h1>
    @if(!page.isVisited())
        <p>Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки</p>
    @endif
    <p>Welcome to the home page!</p>
    <a href="${NamedRoutes.usersPath()}">View Users</a>
`
)
