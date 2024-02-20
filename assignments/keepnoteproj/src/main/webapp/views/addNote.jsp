<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.rtl.min.css"
        integrity="sha384-nU14brUcp6StFntEOOEBvcJm4huWjB0OcIeQ3fltAfSmuZFrkAif0T+UtNGlKKQv" crossorigin="anonymous">
    <title>AddNote</title>
</head>

<body>
    <h1 class="text-center">Add TODO</h1>
    <form action="saveNote" method="post" modelAttribute="note">
        <div class="form-group">
            <input name="noteTitle" class="form-control" placeholder="enter title" />
        </div>
        <div class="form-group">
            <textarea name="noteContent" class="form-control" placeholder="Enter content"
                style="height:300px;"></textarea>
        </div>
        <div class="form-group">
            <input name="noteStatus" class="form-control" placeholder="enter status" />
        </div>

        <div class="container text-center">
            <button class="btn btn-outline-success" type="submit">Add Todo </button>
        </div>
    </form>
</body>

</html>