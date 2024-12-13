const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('report-id').value;
    fetch(`/api/reports/${id}`, {
      method: 'DELETE'
    })
      .then(() => {
        alert('삭제가 완료되었습니다.');
        location.replace('/reports');
      });
  });
}