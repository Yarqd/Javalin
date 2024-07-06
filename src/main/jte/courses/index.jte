@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<html>
<head>
    <title>Хекслет</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    @if(page.getFlash() != null)
        <div class="alert alert-success">${page.getFlash()}</div>
    @endif
    <h1>${page.getHeader()}</h1>
    <form action="${NamedRoutes.coursesPath()}" method="get">
        <input type="search" name="term" value="${page.getTerm()}" placeholder="Search courses..." required>
        <input type="submit" value="Search">
    </form>
    @if(page.getCourses().isEmpty())
        <p>No courses have been added yet</p>
    @else
        @for(var course : page.getCourses())
            <div>
                <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
                <p>${course.getDescription()}</p>
                <form action="/courses/${course.getId()}" method="post" style="display:inline;">
                    <input type="hidden" name="_method" value="delete">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        @endfor
    @endif
</div>
</body>
</html>
