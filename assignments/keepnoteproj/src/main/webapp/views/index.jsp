<%@page isELIgnored="false" %>
    <%@page import="java.util.List" %>
        <%@page import="cgg.keepnotes.keepnoteproj.entities.Note" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.rtl.min.css"
                    integrity="sha384-nU14brUcp6StFntEOOEBvcJm4huWjB0OcIeQ3fltAfSmuZFrkAif0T+UtNGlKKQv"
                    crossorigin="anonymous">
                <title>Notes</title>
            </head>

            <body>
                <div class="container mt-3">
                    <div class="col-md-10">
                        <div class="row mt-5">
                            <h1 class="text-center">Notes</h1>
                            <%List<Note> notes=(List<Note>)request.getAttribute("notes");
                                    if (notes != null) {

                                    for(Note note:notes){%>
                                    <h3>
                                        <%=note.getNoteTitle()%>
                                    </h3>
                                    <p>
                                        <%=note.getNoteContent()%>
                                    </p>
                                    <h4>
                                        <%=note.getNoteStatus()%>
                                    </h4>
                                    <%} } else{%>
                                        <p>No notes are available</p>
                                        <%}%>
                        </div>
                        <div class="container text-center"> <a href="addNote" class=" btn btn-primary ">ADD
                                Note</a></div>
                    </div>

            </body>

            </html>