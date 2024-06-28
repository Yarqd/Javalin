@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <h1>${page.getUser().getName()} ${page.getUser().getPassword()}</h1>
    <p>Email: ${page.getUser().getEmail()}</p>
`
)
