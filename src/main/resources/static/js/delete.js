(function(){
  function openDeleteModal(studentId, studentName){
    var form = document.getElementById('deleteForm');
    if (form) form.action = '/students/delete/' + studentId;
    var nameEl = document.getElementById('deleteStudentName');
    if (nameEl) nameEl.textContent = studentName || '';
    var modalEl = document.getElementById('deleteModal');
    if (!modalEl) return;
    var modal = new bootstrap.Modal(modalEl);
    modal.show();
  }

  function bindDeleteButtons(){
    var buttons = document.querySelectorAll('.btn-delete');
    buttons.forEach(function(btn){
      btn.addEventListener('click', function(e){
        e.preventDefault();
        var id = this.getAttribute('data-student-id');
        var name = this.getAttribute('data-student-name');
        openDeleteModal(id, name);
      });
    });
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', bindDeleteButtons);
  } else {
    bindDeleteButtons();
  }
})();
