@import org.example.hexlet.NamedRoutes

@template.layout.page(
content = @`
    <form action="${NamedRoutes.sessionsPath()}" method="post">
        <input type="email" placeholder="Email" name="email" required />
        <input type="password" placeholder="Password" name="password" required />
        <input type="submit" value="Login" />
    </form>
`
)
