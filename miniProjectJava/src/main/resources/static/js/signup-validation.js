document.addEventListener('DOMContentLoaded', function() {

    // 폼 ID를 HTML의 <form> 태그 ID와 일치시킵니다.
    const form = document.getElementById('signup-form-element');

    // (중요) form이 페이지에 존재할 때만 스크립트를 실행
    if (form) {
        const email = document.getElementById('email');
        const password = document.getElementById('password');
        const passwordCheck = document.getElementById('password-check');
        const username = document.getElementById('username');

        // 정규 표현식 (Regex)
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        const usernameRegex = /^[a-zA-Z0-9_-]{3,16}$/;

        // 폼 제출(submit) 이벤트 리스너
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const isEmailValid = validateEmail();
            const isPasswordValid = validatePassword();
            const isPasswordCheckValid = validatePasswordCheck();
            const isUsernameValid = validateUsername();

            if (isEmailValid && isPasswordValid && isPasswordCheckValid && isUsernameValid) {
                // 실제 서버로 폼 데이터 전송
                // form.submit();
            } else {
                alert('입력 정보를 다시 확인해주세요.');
            }
        });

        // 실시간 유효성 검사
        email.addEventListener('input', validateEmail);
        password.addEventListener('input', validatePassword);
        passwordCheck.addEventListener('input', validatePasswordCheck);
        username.addEventListener('input', validateUsername);

        // --- 유효성 검사 헬퍼 함수 ---

        function validateEmail() {
            if (email.value === '') {
                showError(email, 'email-error', '필수 입력 항목입니다.');
                return false;
            } else if (!emailRegex.test(email.value)) {
                showError(email, 'email-error', '유효한 이메일 주소를 입력해주세요.');
                return false;
            } else {
                showSuccess(email, 'email-error');
                return true;
            }
        }

        function validatePassword() {
            if (password.value === '') {
                showError(password, 'password-error', '필수 입력 항목입니다.');
                return false;
            } else if (!passwordRegex.test(password.value)) {
                showError(password, 'password-error', '최소 8자, 하나 이상의 문자와 숫자를 포함해야 합니다.');
                return false;
            } else {
                showSuccess(password, 'password-error');
                validatePasswordCheck();
                return true;
            }
        }

        function validatePasswordCheck() {
            if (passwordCheck.value === '') {
                showError(passwordCheck, 'password-check-error', '필수 입력 항목입니다.');
                return false;
            } else if (password.value !== passwordCheck.value) {
                showError(passwordCheck, 'password-check-error', '비밀번호가 일치하지 않습니다.');
                return false;
            } else {
                showSuccess(passwordCheck, 'password-check-error');
                return true;
            }
        }

        function validateUsername() {
            if (username.value === '') {
                showError(username, 'username-error', '필수 입력 항목입니다.');
                return false;
            } else if (!usernameRegex.test(username.value)) {
                showError(username, 'username-error', '3~16자의 영문자, 숫자, 밑줄(_), 하이픈(-)만 사용 가능합니다.');
                return false;
            } else {
                showSuccess(username, 'username-error');
                return true;
            }
        }

        // --- UI 헬퍼 함수 (클래스 이름 수정됨) ---

        function showError(input, errorElementId, message) {
            const errorElement = document.getElementById(errorElementId);
            // [수정] '.error' 대신 BEM Modifier 클래스 사용
            input.classList.add('signup-form__input--error');
            input.classList.remove('signup-form__input--success');
            errorElement.textContent = message;
            errorElement.style.display = 'block';
        }

        function showSuccess(input, errorElementId) {
            const errorElement = document.getElementById(errorElementId);
            // [수정] '.success' 대신 BEM Modifier 클래스 사용
            input.classList.remove('signup-form__input--error');
            input.classList.add('signup-form__input--success');
            errorElement.style.display = 'none';
        }
    }
});