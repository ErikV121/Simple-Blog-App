const anonymousToggle = document.getElementById('anonymousToggle');
const authorInput = document.getElementById('author');

anonymousToggle.addEventListener('change', function () {
    if (this.checked) {
        authorInput.value = 'Anonymous';
        authorInput.setAttribute('readonly', true);
    } else {
        authorInput.value = '';
        authorInput.removeAttribute('readonly');
    }
});

