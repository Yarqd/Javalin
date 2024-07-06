@import org.example.hexlet.NamedRoutes

<html>
<head>
    <title>Create Course</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <h1>Create a new course</h1>
    <form action="${NamedRoutes.coursesPath()}" method="post">
        <div class="form-group">
            <label for="name">Course Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Create Course</button>
    </form>
</div>
</body>
</html>
