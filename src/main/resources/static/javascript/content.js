// const sideMenu = document.querySelector("aside");
const menuBtn = document.querySelector("menu-btn");
const closeBtn = document.querySelector("#close-btn");

// //show sidebar
// menuBtn.addEventListener('click', () => {
//     sideMenu.style.display = 'block';
// })
//
// // close sidebar
// closeBtn.addEventListener('click', () => {
//     sideMenu.style.display = 'none';
// })

    $(document).ready(function(){
        //jquery for expand and collapse the sidebar
        $('.menu-btn').click(function(){
            $('.side-bar').addClass('active');
            $('.menu-btn').css("visibility", "hidden");
        });

        $('.close-btn').click(function(){
            $('.side-bar').removeClass('active');
            $('.menu-btn').css("visibility", "visible");
        });
    });
// ----------------- TASKS ADMIN ---------------

//show alerts
var selectedRow = null;

function showAlert(message, className) {
    const div = document.createElement("div");
    div.className = `alert alert-${className}`;

    div.appendChild(document.createTextNode(message));
    const container = document.querySelector(".Wrapper");
    const main = document.querySelector(".main");
    container.insertBefore(div, main);

    setTimeout(() => document, querySelector(".alert").remove(), 3000);

}

// clear all fields
function clearfields() {
    document.querySelector("#title").value = "";
    document.querySelector("#description").value = "";
    document.querySelector("#assignedTo").value = "";
    document.querySelector("#dueDate").value = "";
    document.querySelector("#dueTime").value = "";
    // document.querySelector("#status").value = "";
    // document.querySelector("#actions").value = "";


}

// Add data

document.querySelector("#task-form").addEventListener("submit", (e) => {
    e.preventDefault();

    const title = document.querySelector("#title").value;
    const description = document.querySelector("#description").value;
    const assignedTo = document.querySelector("#assignedTo").value;
    const dueDate = document.querySelector("#dueDate").value;
    const dueTime = document.querySelector("#dueTime").value;
    // const status = document.querySelector("#status").value;
    // const actions = document.querySelector("#actions").value;


    // validate
    if (title == "" || description == "" || assignedTo == "" || dueDate == "" || dueTime == "" || status == "") {
        showAlert("Please fill in the fields", "danger");
    } else {
        if (selectedRow == null) {
            const list = document.querySelector("#tasks-list");
            const row = document.createElement("tr");

            row.innerHTML = `
            <td> ${title} </td>
            <td> ${description} </td>
            <td> ${assignedTo} </td>
            <td> ${dueDate} </td>
            <td> ${dueTime} </td>
            <td> ${status} </td>
            <td>
            <a href="#" class="warning">Edit </a>
            <a href="#" class="danger">Delete </a>
            `;
            list.appendChild(row);
            selectedRow = null;
            showAlert("Tasks added", "success")
        } else {
            selectedRow.children[0].textContent = title;
            selectedRow.children[1].textContent = description;
            selectedRow.children[2].textContent = assignedTo;
            selectedRow.children[3].textContent = dueDate;
            selectedRow.children[4].textContent = dueTime;
            // selectedRow.children[5].textContent = status;
            // selectedRow.children[4].textContent = actions;

            selectedRow = null;
            showAlert("Task edited", "warning");

        }


        clearfields();
    }
    //edit data

    documnet.querySelector("#tasks-list").addEventListener("click", (e) => {
        target = e.target;
        if (target.classList.contains("edit")) {
            selectedRow = target.parentElement.parentElement;
            document.querySelector("#title").value = selectedRow, children[0].textContent;
            document.querySelector("#description").value = selectedRow, children[1].textContent;
            document.querySelector("#assignedTo").value = selectedRow, children[2].textContent;
            document.querySelector("#dueDate").value = selectedRow, children[3].textContent;
            document.querySelector("#dueTime").value = selectedRow, children[4].textContent;
            // document.querySelector("#status").value = selectedRow, children[4].textContent;
            // document.querySelector("#actions").value = selectedRow, children[5].textContent;


        }
    })
});

const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#id_password');

togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});

// delete data
document.querySelector("#tasks-list").addEventListener("click", (e) => {
    target = e.target;
    if (target.tasksList.contains("delete")) {
        target.parentElement.parentElement.remove();
        showAlert("Tasks deleted", "danger");
    }
})

document.getElementById("success").addEventListener("click", function() {
    var result = confirm("Are you sure?");
    if (result === true) {
        alert("You clicked 'Yes'");
    } else {
        alert("You clicked 'Cancel'");
    }
});



// --------------------------- ADMIN ADD DEPARTMENT -------------------
window.addEventListener('load', () => {
    const form = document.querySelector("#department-form");
    const input = document.querySelector("#department-input");
    const list_el = document.querySelector("#departments");

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const department = input.value;
        if (!task) {
            alert("please fill out the department");
            return;
        }
        const department_el = document.createElement("div");
        department_el.classList.add("department");

        const department_content_el = document.createElement("div");
        department_content_el.classList.add("content");
        // department_content_el.innerText = department;

        department_el.appendChild(department_content_el);

        const department_input_el = document.createElement("input");
        department_input_el.classList.add("text");
        department_input_el.type = "text";
        department_input_el.value = department;
        department_input_el.setAttribute("readonly", "readonly");

        department_content_el.appendChild(department_input_el);

        const department_actions_el = document.createElement("div");
        department_actions_el.classList.add("actions");

        const department_edit_el = document.createElement("button");
        department_edit_el.classList.add("edit");
        department_edit_el.innerHTML = "Edit";

        const department_delete_el = document.createElement("button");
        department_delete_el.classList.add("delete");
        department_delete_el.innerHTML = "Delete";

        department_actions_el.appendChild(department_edit_el);
        department_actions_el.appendChild(department_delete_el);

        department_el.appendChild(department_actions_el);

        list_el.appendChild(department_el);

        input.value = "";

        department_edit_el.addEventListener('click', () => {
            if (department_edit_el.innerText.toLowerCase() == "edit") {
                department_input_el.removeAttribute('readonly');
                department_input_el.focus();
                department_edit_el.innerText = "Save";
            } else {
                department_input_el.setAttribute('readonly', 'readonly');
                department_edit_el.innerText = "Edit";
            }
        });

        department_delete_el.addEventListener('click', () => {
            list_el.removeChild(department_el);
        })
    })
})