@import org.example.hexlet.NamedRoutes
@template.layout.page(
content = @`
    <p>Welcome to the home page!</p>
    <a href="${NamedRoutes.usersPath()}">View Users</a>
<%--    <a href="/users">View Users</a>--%>
`
)
