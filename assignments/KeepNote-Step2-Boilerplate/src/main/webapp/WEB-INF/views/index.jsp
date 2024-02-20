<!DOCTYPE html>
<html lang="en">

<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.rtl.min.css"
		integrity="sha384-nU14brUcp6StFntEOOEBvcJm4huWjB0OcIeQ3fltAfSmuZFrkAif0T+UtNGlKKQv" crossorigin="anonymous">
	<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
	<div class="container">
		<div class="card">
			<div class="card-header">N
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
						<label for="exampleInputPassword1" class="form-label">Password</label>
						<textarea rows="5" class="form-control" id="content" name="noteContent"></textarea>
					</div>
					<div class="mb-3">
						<select class="form-select" aria-label="Default select example" name="noteStatus">
							<option selected>status</option>
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
								<td class="fw-bold">&#8377;${note.getNoteStatus()}</td>
								<td></td>
								<td>
									<a href="delete/${p.id}"><i class="fa fa-trash text-danger" style="font-size:20px"
											aria-hidden=" true"></i></a>
									<a href="update/${p.id}"><i class="fa fa-pencil text-primary" style="font-size:20px"
											aria-hidden="true"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->

</body>

</html>