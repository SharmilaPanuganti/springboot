<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.rtl.min.css"
                integrity="sha384-nU14brUcp6StFntEOOEBvcJm4huWjB0OcIeQ3fltAfSmuZFrkAif0T+UtNGlKKQv"
                crossorigin="anonymous">
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
                rel="stylesheet">

            <title>Keep-Board</title>
        </head>

        <body>
            <!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->

            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <h2>Add Notes</h2>

                    </div>

                    <div class="card-body">
                        <form action="add" method="post" modelAttribute="note">
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input type="text" class="form-control" id="title" aria-describedby="emailHelp"
                                    name="noteTitle">

                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Content</label>
                                <textarea rows="5" class="form-control" id="content" name="noteContent"></textarea>
                            </div>
                            <div class="mb-3">
                                <select class="form-select" aria-label="Default select example" name="noteStatus">
                                    <option selected disabled>status</option>
                                    <option value="Completed">Completed</option>
                                    <option value="Pending">Pending</option>
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Content </th>
                                    <th scope="col">Create at</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${notes}" var="note">
                                    <tr>
                                        <th scope="row">${note.getNoteId()}</th>
                                        <td>${note.getNoteTitle() }</td>
                                        <td>${note.getNoteContent() }</td>
                                        <td>${note.getCreatedAt()}</td>
                                        <td>${note.getNoteStatus()}</td>
                                        <td>
                                            <a href="delete/${note.getNoteId()}"><i class="fa fa-trash text-danger"
                                                    style="font-size:20px" aria-hidden=" true"></i></a>
                                            <a href="" data-bs-toggle="modal"
                                                data-bs-target="#exampleModal-${note.getNoteId()}"><i
                                                    class="fa fa-pencil text-primary" style="font-size:20px"
                                                    aria-hidden="true"></i></a>
                                        </td>
                                    </tr>
                                    <div class="modal fade" id="exampleModal-${note.getNoteId()}" tabindex="-1"
                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Update</h1>

                                                </div>
                                                <div class="modal-body">
                                                    <form action="update/${note.getNoteId()}" method="post">
                                                        <div class="mb-3">
                                                            <label for="title" class="form-label">Title</label>
                                                            <input type="text" class="form-control" id="title"
                                                                aria-describedby="emailHelp" name="noteTitle"
                                                                value="${note.getNoteTitle()}">

                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="exampleInputPassword1"
                                                                class="form-label">Content</label>
                                                            <textarea rows="5" class="form-control" id="content"
                                                                name="noteContent">${note.getNoteContent()}</textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <select class="form-select"
                                                                aria-label="Default select example" name="noteStatus">
                                                                <option selected disabled>status</option>
                                                                <option value="Completed">Completed</option>
                                                                <option value="Pending">Pending</option>
                                                            </select>
                                                        </div>

                                                        <button type="submit" class="btn btn-primary">Submit</button>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Button trigger modal -->


            <!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                crossorigin="anonymous"></script>
        </body>

        </html>