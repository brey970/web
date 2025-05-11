document.addEventListener('DOMContentLoaded', () => {
    console.log("Script loaded and DOMContentLoaded");

    // --- Element Selectors ---
    const appContainer = document.querySelector('.app-container');
    const loginPage = document.getElementById('login-page');
    const forgotPasswordPage = document.getElementById('forgot-password-page');
    const signupPage = document.getElementById('signup-page'); // New
    const mainAppContent = document.getElementById('main-app-content');
    const loadingScreen = document.getElementById('loading-screen');

    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const togglePasswordBtn = document.getElementById('togglePassword');
    const loginBtn = document.getElementById('login-btn');
    const loginToggleBtn = document.getElementById('login-toggle-btn'); // On Login Page
    const signupToggleBtn = document.getElementById('signup-toggle-btn'); // On Login Page
    const forgotPasswordLink = document.querySelector('.forgot-password');

    const forgotEmailInput = document.getElementById('forgot-email');
    const sendResetLinkBtn = document.getElementById('send-reset-link-btn');
    const backToLoginBtn = document.getElementById('back-to-login-btn');

    // Sign Up Page Elements
    const loginToggleBtnSignup = document.getElementById('login-toggle-btn-signup'); // On Signup Page
    const signupToggleBtnSignup = document.getElementById('signup-toggle-btn-signup'); // On Signup Page
    const fullNameSignupInput = document.getElementById('full-name-signup');
    const emailSignupInput = document.getElementById('email-signup');
    const passwordSignupInput = document.getElementById('password-signup');
    const togglePasswordSignupBtn = document.getElementById('togglePasswordSignup');
    const confirmPasswordSignupInput = document.getElementById('confirm-password-signup');
    const toggleConfirmPasswordSignupBtn = document.getElementById('toggleConfirmPasswordSignup');
    const createAccountBtn = document.getElementById('create-account-btn');


    const menuBtn = document.getElementById('menu-btn');
    const closeMenuBtn = document.getElementById('close-menu-btn');
    const sideMenu = document.getElementById('side-menu');
    const overlay = document.getElementById('overlay');
    const headerTitle = document.getElementById('header-title');
    const headerActions = document.getElementById('header-actions');

    const bottomNavItems = document.querySelectorAll('.bottom-nav .nav-item');
    const darkModeToggle = document.getElementById('dark-mode-toggle');

    const searchQuotesInput = document.getElementById('search-quotes');
    const quotesContainer = document.getElementById('quotes-container');

    const profilePicDisplay = document.getElementById('profile-pic-display');
    const profileNameDisplay = document.getElementById('profile-name-display');
    const profileSubtitleDisplay = document.getElementById('profile-subtitle-display');
    const profileBadgesDisplay = document.getElementById('profile-badges-display');
    const profileAboutMeDisplay = document.getElementById('profile-about-me-display');

    const editProfilePicDisplay = document.getElementById('profile-pic-edit-display');
    const editProfileNameInput = document.getElementById('edit-profile-name');
    const editProfileSubtitleInput = document.getElementById('edit-profile-subtitle');
    const editProfileBadgesContainer = document.getElementById('edit-profile-badges');
    const editProfileAboutMeTextarea = document.getElementById('edit-profile-about-me');

    const chatPage = document.getElementById('chat-page');
    const chatWindow = document.getElementById('chat-window');
    const chatMessageInput = document.getElementById('chat-message-input');
    const sendChatBtn = document.getElementById('send-chat-btn');

    const categoryPageEl = document.getElementById('category-page');
    const categoryTabsContainer = categoryPageEl ? categoryPageEl.querySelector('.category-tabs') : null;
    const categoryContentArea = document.getElementById('category-content-area');
    const categoryMenuList = document.getElementById('category-menu-list');

    const settingsPage = document.getElementById('settings-page');
    const settingsItems = settingsPage ? settingsPage.querySelectorAll('.settings-item[data-page]') : [];
    const logoutMenuBtn = document.getElementById('logout-menu-btn');
    const logoutSettingsBtn = document.getElementById('logout-settings-btn');

    const accountProfileNameInput = document.getElementById('account-profile-name');
    const accountProfileSubtitleInput = document.getElementById('account-profile-subtitle');
    const accountProfileAboutTextarea = document.getElementById('account-profile-about');
    const accountEmailDisplay = document.getElementById('account-email-display');
    const saveAccountProfileBtn = document.getElementById('save-account-profile-btn');
    const changePasswordBtn = document.getElementById('change-password-btn');
    const deactivateAccountBtn = document.getElementById('deactivate-account-btn');

    let currentPageId = 'login-page';
    let previousPageId = 'login-page';

    let currentProfileData = {
        name: "Blossom Powerpuff",
        subtitle: "Leading with strength and smarts!",
        about: "Commander and leader. Self-proclaimed leader of the Powerpuff Girls.",
        badges: ["Gender", "Age"],
        initials: "BP",
        email: "blossom@powerpuff.com"
    };

    const allBadges = ["Gender", "Religious", "Disability", "Age", "Sexual Orientation", "Appearance-Based"];
    const badgeColors = {
        "Gender": "gender", "Religious": "religious", "Disability": "disability",
        "Age": "age", "Sexual Orientation": "sexual-orientation", "Appearance-Based": "appearance-based"
    };
    const mainPages = ['home-page', 'profile-page', 'chat-page', 'settings-page'];


    const quotesData = [
        { text: "Our ability to reach unity in diversity will be the beauty and the test of our civilization.", author: "Mahatma Gandhi" },
        { text: "Darkness cannot drive out darkness; only light can do that. Hate cannot drive out hate; only love can do that.", author: "Martin Luther King Jr." },
        { text: "The mind is everything. What you think you become.", author: "Buddha" },
        { text: "An unexamined life is not worth living.", author: "Socrates" },
        { text: "Be kind, for everyone you meet is fighting a hard battle.", author: "Plato" },
        { text: "Diversity is not about how we differ. Diversity is about embracing one another's uniqueness.", author: "Ola Joseph" },
        { text: "Injustice anywhere is a threat to justice everywhere.", author: "Martin Luther King Jr." },
        { text: "No one is born hating another person because of the color of his skin, or his background, or his religion. People must learn to hate, and if they can learn to hate, they can be taught to love, for love comes more naturally to the human heart than its opposite.", author: "Nelson Mandela" },
        { text: "The highest result of education is tolerance.", author: "Helen Keller" },
        { text: "Our lives begin to end the day we become silent about things that matter.", author: "Martin Luther King Jr." },
        { text: "Diversity: the art of thinking independently together.", author: "Malcolm Forbes" },
        { text: "We may have all come on different ships, but we're in the same boat now.", author: "Martin Luther King Jr." },
        { text: "If you want to lift yourself up, lift up someone else.", author: "Booker T. Washington" },
        { text: "The only way to deal with an unfree world is to become so absolutely free that your very existence is an act of rebellion.", author: "Albert Camus" },
        { text: "Prejudice is a burden that confuses the past, threatens the future and renders the present inaccessible.", author: "Maya Angelou" },
    ];

    const categoryData = {
        "gender": {
            id: "gender",
            label: "Gender",
            tabs: {
                impact: "<p>Gender discrimination affects individuals in workplaces, education, and daily life, leading to unequal opportunities, pay gaps, and limited career progression. It can also manifest as harassment and contribute to mental health issues.</p>",
                solutions: "<p>Promote gender equality through comprehensive education, inclusive policy changes, and actively challenging stereotypes. Support organizations working for gender rights and advocate for equal representation in leadership roles.</p>",
                problems: "<p>Deep-rooted societal norms, unconscious biases, lack of representation in key sectors, workplace harassment, and the gender pay gap are major problems. Intersectionality also means gender discrimination can be compounded by other forms of bias.</p>",
                laws: "<p>Various laws like the Equal Pay Act, Title VII of the Civil Rights Act (prohibiting sex-based discrimination in employment), and Title IX (prohibiting sex-based discrimination in education programs) aim to prevent gender discrimination in many countries.</p>"
            },
            questions: [
                { id: 'gq1', text: "Have you ever witnessed gender bias in a professional setting (e.g., hiring, promotion, task assignment)?", feedbackYes: "Recognizing bias is the first step. Consider how you might safely challenge it or support those affected.", feedbackNo: "That's positive. It's still important to be aware that bias can be subtle." },
                { id: 'gq2', text: "Do you believe media (TV, movies, ads) generally portrays diverse gender identities and roles accurately and respectfully?", feedbackYes: "Positive representation is growing in some areas, but there's still a long way to go for full and nuanced portrayal.", feedbackNo: "Media literacy is crucial to identify and challenge harmful stereotypes. Seek out diverse creators and narratives." },
            ]
        },
        "religious": {
            id: 'religious',
            label: 'Religious',
            tabs: {
                impact: "<p>Religious discrimination can lead to social exclusion, denial of services, hate crimes, restrictions on practicing faith, and psychological distress. It creates an environment of fear and can hinder individuals' full participation in society.</p>",
                solutions: "<p>Promoting interfaith dialogue and education to foster mutual respect and understanding, ensuring freedom of religion in law and practice, actively protecting places of worship and religious minorities, and challenging religious intolerance and stereotypes in media and public discourse.</p>",
                problems: "<p>Religious intolerance, extremism, misinformation and stereotypes about different faiths, laws that favor one religion or restrict practices, societal pressure to conform, and the misuse of religious texts to justify discrimination are major problems.</p>",
                laws: "<p>Constitutional guarantees of religious freedom, laws against hate speech and crimes targeting religious groups, and international human rights laws protecting freedom of thought, conscience, and religion form the legal basis for combating religious discrimination.</p>",
            },
            questions: [
                { id: 'rq1', text: "Have you ever made an assumption about someone's beliefs or practices based on their religious attire or symbols?", feedbackYes: "It's a common shortcut. Being aware helps us remember symbols don't tell the whole story of one's faith or character.", feedbackNo: "That shows respect for individual diversity. Judging people on character, not external markers, is key to fairness." },
            ]
        },
        "disability": {
            id: "disability",
            label: "Disability",
            tabs: {
                impact: "<p>Discrimination against individuals with disabilities leads to exclusion from employment, education, social activities, and inaccessible environments, severely limiting opportunities and independence.</p>",
                solutions: "<p>Enforce accessibility laws (e.g., ADA), promote universal design, challenge ableist attitudes, ensure inclusive education and employment practices, and support disability advocacy groups.</p>",
                problems: "<p>Physical barriers, lack of accommodations, societal stigma and misconceptions, insufficient legal protections or enforcement, and underestimation of capabilities are significant problems.</p>",
                laws: "<p>Laws like the Americans with Disabilities Act (ADA) in the US, and similar legislation in other countries, aim to prohibit discrimination and ensure equal opportunity for people with disabilities.</p>"
            },
            questions: [
                { id: 'dq1', text: "Are public spaces and transportation in your community generally accessible to people with various disabilities?", feedbackYes: "That's great! Accessibility benefits everyone, not just those with disabilities.", feedbackNo: "Identifying accessibility barriers is the first step. Advocating for improvements can make a big difference." },
            ]
        },
        "age": {
            id: 'age',
            label: 'Age',
            tabs: {
                impact: "<p>Ageism impacts individuals across the lifespan, leading to stereotypes, prejudice, and discrimination in employment, healthcare, social inclusion, and media representation. It can affect financial stability, career opportunities, and overall well-being.</p>",
                solutions: "<p>Solutions involve challenging ageist stereotypes, promoting intergenerational connections and programs, enforcing laws against age discrimination, encouraging lifelong learning, and valuing the contributions of all age groups.</p>",
                problems: "<p>Pervasive stereotypes about capabilities based on age, lack of awareness of ageism's impact, discriminatory workplace policies, and societal pressure for older individuals to 'step aside' or younger individuals to 'wait their turn.'</p>",
                laws: "<p>Laws like the Age Discrimination in Employment Act (ADEA) in the US protect older workers. However, legal protections against ageism for younger people are less common, and enforcement can be challenging.</p>",
            },
            questions: [
                { id: 'agq1', text: "Do you find yourself thinking older people are 'out of touch' or younger people 'lack experience' based solely on age?", feedbackYes: "Age-based stereotypes are common. Reflecting on these helps us see individuals for their unique skills.", feedbackNo: "That's a great mindset! Valuing diverse experiences across all age groups enriches our communities." },
            ]
        },
        "sexual_orientation": {
            id: 'sexual_orientation',
            label: 'Sexual Orientation',
            tabs: {
                impact: "<p>Discrimination based on SOGIE (Sexual Orientation, Gender Identity, and Expression) leads to harassment, violence, denial of rights in employment, housing, healthcare, and social exclusion, causing significant mental health tolls.</p>",
                solutions: "<p>Enacting comprehensive SOGIE equality laws, promoting LGBTQ+ inclusion in all sectors, providing education and sensitivity training, supporting LGBTQ+ advocacy, ensuring access to affirming healthcare, and challenging all forms of homo/bi/transphobia.</p>",
                problems: "<p>Lack of legal protections, strong societal stigma and prejudice, religiously or culturally justified discrimination, high rates of violence and hate crimes, and barriers to essential services.</p>",
                laws: "<p>While not universal, some jurisdictions have specific laws protecting against SOGIE-based discrimination. International human rights bodies increasingly affirm these rights. Advocacy for comprehensive SOGIE Equality Laws continues globally.</p>",
            },
            questions: [
                { id: 'soq1', text: "Have you ever assumed someone's sexual orientation or gender identity based on their appearance or mannerisms?", feedbackYes: "It's easy to fall into stereotypes. Remembering SOGIE is diverse and personal helps us respect individual identities.", feedbackNo: "That's fantastic. Letting people self-identify and respecting their stated identity is fundamental." },
            ]
        },
        "appearance_based": {
            id: "appearance_based",
            label: "Appearance-Based",
            tabs: {
                impact: "<p>Appearance-based discrimination (lookism) affects individuals based on physical attractiveness, weight, height, style, or other features, leading to bias in hiring, social settings, and media, impacting self-esteem and opportunities.</p>",
                solutions: "<p>Promote body positivity and acceptance of diverse appearances, challenge narrow beauty standards in media and advertising, advocate for policies against appearance-based discrimination where relevant (e.g., weight discrimination), and focus on skills and character over looks.</p>",
                problems: "<p>Pervasive societal beauty standards, media influence, unconscious bias, lack of legal protection in most areas, and bullying/harassment based on appearance are major issues.</p>",
                laws: "<p>Specific legal protections against appearance-based discrimination are rare, though some localities might have ordinances against weight or height discrimination. It's often an unacknowledged form of bias.</p>"
            },
            questions: [
                { id: 'apq1', text: "Have you ever judged someone's competence or personality based on their physical appearance or clothing style before getting to know them?", feedbackYes: "First impressions are often visual, but it's important to consciously look beyond appearance.", feedbackNo: "That's a commendable trait. Focusing on character and abilities fosters fairer judgments." },
            ]
        }
    };

    function showPage(pageId, title, isSubPage = false) {
        console.log(`Navigating to: ${pageId}, Title: ${title}, IsSubPage: ${isSubPage}, Prev: ${previousPageId}`);
        const allAppPages = appContainer.querySelectorAll('.page');

        allAppPages.forEach(p => p.classList.add('hidden'));

        const targetPage = document.getElementById(pageId);

        // Updated condition to include signup-page
        if (pageId === 'login-page' || pageId === 'forgot-password-page' || pageId === 'loading-screen' || pageId === 'signup-page') {
            if (mainAppContent) mainAppContent.classList.add('hidden');
            if (targetPage) {
                targetPage.classList.remove('hidden');
                if (pageId === 'loading-screen') targetPage.style.display = 'flex';
                else if (pageId !== 'loading-screen' && targetPage.style.display === 'flex') targetPage.style.display = ''; // Reset display for other auth pages
            }
             // Manage active state of toggle buttons
            if (pageId === 'login-page') {
                if (loginToggleBtn) loginToggleBtn.classList.add('active');
                if (signupToggleBtn) signupToggleBtn.classList.remove('active');
                // Ensure signup page toggles reflect this if they were visible (though they shouldn't be)
                if (loginToggleBtnSignup) loginToggleBtnSignup.classList.add('active');
                if (signupToggleBtnSignup) signupToggleBtnSignup.classList.remove('active');
            } else if (pageId === 'signup-page') {
                if (loginToggleBtn) loginToggleBtn.classList.remove('active');
                if (signupToggleBtn) signupToggleBtn.classList.add('active');
                // Activate correct toggles on the signup page itself
                if (loginToggleBtnSignup) loginToggleBtnSignup.classList.remove('active');
                if (signupToggleBtnSignup) signupToggleBtnSignup.classList.add('active');
            }

        } else { // For main app pages
            if (mainAppContent) mainAppContent.classList.remove('hidden');
            if (targetPage) targetPage.classList.remove('hidden');

            if (pageId === 'home-page') {
                if (quotesContainer) renderQuotes();
                slideIndexGlobal = 1;
                displaySlide(slideIndexGlobal);
                clearTimeout(slideshowTimeoutGlobal);
                slideshowTimeoutGlobal = setTimeout(autoAdvanceSlides, SLIDESHOW_INTERVAL);
            } else {
                if (currentPageId === 'home-page') { // If navigating away from home
                    clearTimeout(slideshowTimeoutGlobal);
                }
            }
            updateHeader(title || targetPage.dataset.title || 'App', isSubPage, pageId);
            updateActiveBottomNav(pageId);

            if (pageId === 'profile-page' && profilePicDisplay) loadProfileData();
            if (pageId === 'account-sub-page' && accountProfileNameInput) loadAccountPageData();
            if (pageId === 'edit-profile-page' && editProfileNameInput) loadMainEditProfileData();
        }

        if (pageId !== currentPageId) {
            previousPageId = currentPageId;
        }
        currentPageId = pageId;

        if (sideMenu && sideMenu.classList.contains('open')) {
             if (pageId !== 'category-page' && pageId !== 'about-app-page' && !mainPages.includes(pageId) && !pageId.endsWith('-sub-page')) {
                sideMenu.classList.remove('open');
                if (overlay) overlay.classList.add('hidden');
             }
        }
    }


    function updateHeader(title, isSubPage, currentViewingPageId) {
        if (!headerTitle || !menuBtn || !headerActions) {
            console.error("Header elements not found!");
            return;
        }
        headerTitle.textContent = title;
        headerActions.innerHTML = '';

        const mainAppHeader = document.querySelector('#main-app-content > header');
        if (!mainAppHeader) return;

        const existingBackBtn = mainAppHeader.querySelector('.icon-btn.back-btn');
        if (existingBackBtn) existingBackBtn.remove();

        const isCurrentlyOnMainAppPage = mainPages.includes(currentViewingPageId);

        if (isSubPage || !isCurrentlyOnMainAppPage) {
            menuBtn.style.display = 'none';
            const backBtn = document.createElement('button');
            backBtn.innerHTML = '<i class="fas fa-arrow-left"></i>';
            backBtn.className = 'icon-btn back-btn';
            backBtn.onclick = () => {
                let targetBackPage = 'home-page';
                let targetBackTitle = 'Home';

                if (currentViewingPageId === 'edit-profile-page') {
                    targetBackPage = 'profile-page';
                    targetBackTitle = 'Profile';
                } else if (currentViewingPageId.endsWith('-sub-page') &&
                           currentViewingPageId !== 'about-app-page' &&
                           !currentViewingPageId.startsWith('category')) {
                    targetBackPage = 'settings-page';
                    targetBackTitle = 'Settings';
                } else if (currentViewingPageId === 'category-page' || currentViewingPageId === 'about-app-page') {
                    if (previousPageId && mainPages.includes(previousPageId) && previousPageId !== 'login-page' && previousPageId !== 'signup-page') {
                        targetBackPage = previousPageId;
                        targetBackTitle = previousPageId.split('-')[0].charAt(0).toUpperCase() + previousPageId.split('-')[0].slice(1).replace('-page','');
                        if (targetBackTitle === 'Home') targetBackTitle = 'Home';
                        else if (targetBackTitle === 'Profile') targetBackTitle = 'Profile';
                        else if (targetBackTitle === 'Chat') targetBackTitle = 'Chat';
                        else if (targetBackTitle === 'Settings') targetBackTitle = 'Settings';
                    } else {
                        targetBackPage = 'home-page';
                        targetBackTitle = 'Home';
                    }
                }
                showPage(targetBackPage, targetBackTitle, mainPages.includes(targetBackPage) ? false : !targetBackPage.endsWith('-sub-page'));
            };
            mainAppHeader.insertBefore(backBtn, mainAppHeader.firstChild);
        } else {
            menuBtn.style.display = 'block';
        }

        if (currentViewingPageId === 'profile-page') {
            const editBtn = document.createElement('button');
            editBtn.textContent = 'Edit';
            editBtn.onclick = () => showPage('edit-profile-page', 'Edit Profile', true);
            headerActions.appendChild(editBtn);
        } else if (currentViewingPageId === 'edit-profile-page') {
            const cancelBtn = document.createElement('button');
            cancelBtn.textContent = 'Cancel';
            cancelBtn.onclick = handleCancelMainEditProfile;

            const saveBtn = document.createElement('button');
            saveBtn.textContent = 'Save';
            saveBtn.onclick = handleSaveMainProfile;

            headerActions.appendChild(cancelBtn);
            headerActions.appendChild(saveBtn);
        }
    }

    function updateActiveBottomNav(pageId) {
        if (!bottomNavItems) return;
        bottomNavItems.forEach(item => {
            item.classList.remove('active');
            if (item.dataset.page === pageId) {
                item.classList.add('active');
            }
        });
    }

    function calculateInitials(nameStr) {
        if (!nameStr || typeof nameStr !== 'string') return "U";
        const nameParts = nameStr.trim().split(' ').filter(part => part.length > 0);
        let initials = "";
        if (nameParts.length > 0 && nameParts[0].length > 0) {
            initials += nameParts[0][0].toUpperCase();
            if (nameParts.length > 1 && nameParts[nameParts.length - 1].length > 0 && nameParts[0] !== nameParts[nameParts.length - 1]) {
                initials += nameParts[nameParts.length - 1][0].toUpperCase();
            } else if (initials.length === 1 && nameParts[0].length > 1) {
                // If only one name part, use first two letters if available
                initials += nameParts[0][1].toUpperCase();
            }
        }
        return initials || "U";
    }

    if (togglePasswordBtn && passwordInput) {
        togglePasswordBtn.addEventListener('click', () => {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            togglePasswordBtn.classList.toggle('fa-eye');
            togglePasswordBtn.classList.toggle('fa-eye-slash');
        });
    }

    // Password Toggles for Sign Up Page
    if (togglePasswordSignupBtn && passwordSignupInput) {
        togglePasswordSignupBtn.addEventListener('click', () => {
            const type = passwordSignupInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordSignupInput.setAttribute('type', type);
            togglePasswordSignupBtn.classList.toggle('fa-eye');
            togglePasswordSignupBtn.classList.toggle('fa-eye-slash');
        });
    }
    if (toggleConfirmPasswordSignupBtn && confirmPasswordSignupInput) {
        toggleConfirmPasswordSignupBtn.addEventListener('click', () => {
            const type = confirmPasswordSignupInput.getAttribute('type') === 'password' ? 'text' : 'password';
            confirmPasswordSignupInput.setAttribute('type', type);
            toggleConfirmPasswordSignupBtn.classList.toggle('fa-eye');
            toggleConfirmPasswordSignupBtn.classList.toggle('fa-eye-slash');
        });
    }


    if (loginBtn && emailInput && passwordInput && loginPage && loadingScreen && mainAppContent) {
        loginBtn.addEventListener('click', () => {
            const emailVal = emailInput.value;
            const passwordVal = passwordInput.value;
            if (emailVal && passwordVal) { // Basic validation
                // Simulate successful login
                currentProfileData.email = emailVal;
                currentProfileData.name = emailVal.split('@')[0] || "User"; // Use part before @ as name
                currentProfileData.initials = calculateInitials(currentProfileData.name);
                // Reset some fields for a fresh login
                currentProfileData.subtitle = "Welcome back!";
                currentProfileData.about = "Ready to make a difference today.";
                currentProfileData.badges = ["Age", "Gender"]; // Example default badges on login

                showPage('loading-screen', 'Loading');
                setTimeout(() => {
                    showPage('home-page', 'Home');
                }, 1500);
            } else {
                alert('Please enter email and password.');
            }
        });
    }

    // Create Account Button Logic
    if (createAccountBtn && fullNameSignupInput && emailSignupInput && passwordSignupInput && confirmPasswordSignupInput && loadingScreen && mainAppContent) {
        createAccountBtn.addEventListener('click', () => {
            const fullName = fullNameSignupInput.value.trim();
            const emailVal = emailSignupInput.value.trim();
            const passwordVal = passwordSignupInput.value; // Don't trim password
            const confirmPasswordVal = confirmPasswordSignupInput.value;

            if (!fullName || !emailVal || !passwordVal || !confirmPasswordVal) {
                alert('Please fill in all fields.');
                return;
            }
            if (!emailVal.includes('@') || !emailVal.includes('.')) {
                alert('Please enter a valid email address.');
                return;
            }
            if (passwordVal.length < 6) { // Example: minimum password length
                alert('Password must be at least 6 characters long.');
                return;
            }
            if (passwordVal !== confirmPasswordVal) {
                alert('Passwords do not match.');
                return;
            }

            // If all checks pass, simulate account creation
            currentProfileData.email = emailVal;
            currentProfileData.name = fullName;
            currentProfileData.initials = calculateInitials(currentProfileData.name);
            currentProfileData.subtitle = "Welcome to the community!";
            currentProfileData.about = "Just joined! Looking forward to learning and contributing.";
            currentProfileData.badges = []; // Start with no badges for new user

            showPage('loading-screen', 'Creating Account');
            setTimeout(() => {
                // Clear signup form fields after successful "creation"
                fullNameSignupInput.value = '';
                emailSignupInput.value = '';
                passwordSignupInput.value = '';
                confirmPasswordSignupInput.value = '';
                showPage('home-page', 'Home');
            }, 1500);
        });
    }


    // Auth Toggle Buttons
    // On Login Page:
    if (loginToggleBtn && signupToggleBtn) {
        loginToggleBtn.addEventListener('click', () => {
            showPage('login-page', 'Log In');
        });
        signupToggleBtn.addEventListener('click', () => {
            showPage('signup-page', 'Sign Up');
        });
    }
    // On Sign Up Page:
    if (loginToggleBtnSignup && signupToggleBtnSignup) {
        loginToggleBtnSignup.addEventListener('click', () => {
            showPage('login-page', 'Log In');
        });
        signupToggleBtnSignup.addEventListener('click', () => {
            showPage('signup-page', 'Sign Up');
        });
    }


    if (forgotPasswordLink) {
        forgotPasswordLink.addEventListener('click', (e) => {
            e.preventDefault();
            showPage('forgot-password-page', 'Forgot Password');
        });
    }

    if (sendResetLinkBtn && forgotEmailInput) {
        sendResetLinkBtn.addEventListener('click', () => {
            const email = forgotEmailInput.value.trim();
            if (email === "") {
                alert("Please enter your email address.");
                return;
            }
            if (!email.includes('@') || !email.includes('.')) { // Basic email format check
                alert("Please enter a valid email address.");
                return;
            }
            // Simulate sending link
            console.log(`Simulating sending password reset link to: ${email}`);
            alert(`If ${email} is associated with an account, a password reset link will be sent. (This is a demo)`);
            // Optionally clear input: forgotEmailInput.value = '';
        });
    }

    if (backToLoginBtn) {
        backToLoginBtn.addEventListener('click', () => {
            showPage('login-page', 'Log In');
        });
    }


    if (menuBtn && sideMenu && overlay) {
        menuBtn.addEventListener('click', () => {
            sideMenu.classList.add('open');
            overlay.classList.remove('hidden');
        });
    }
    if (closeMenuBtn && sideMenu && overlay) {
        closeMenuBtn.addEventListener('click', () => {
            sideMenu.classList.remove('open');
            overlay.classList.add('hidden');
        });
    }
    if (overlay && sideMenu) {
        overlay.addEventListener('click', () => {
            sideMenu.classList.remove('open');
            overlay.classList.add('hidden');
        });
    }

    if (categoryMenuList && Object.keys(categoryData).length > 0) {
        Object.values(categoryData).forEach(category => {
            const li = document.createElement('li');
            const button = document.createElement('button');
            button.className = 'category-link';
            button.dataset.categoryId = category.id;
            const colorDotClass = badgeColors[category.label] || 'default'; // Use badgeColors for dot
            button.innerHTML = `<i class="fas fa-circle" style="color: var(--badge-${colorDotClass});"></i> ${category.label}`;
            button.onclick = () => {
                loadCategoryPage(category.id);
                // sideMenu.classList.remove('open'); // Close menu on selection
                // overlay.classList.add('hidden');
            };
            li.appendChild(button);
            categoryMenuList.appendChild(li);
        });
    }

    const aboutAppMenuButton = document.querySelector('.side-menu-item[data-page="about-app-page"]');
    if (aboutAppMenuButton) {
        aboutAppMenuButton.addEventListener('click', () => {
            showPage('about-app-page', 'About This App', true);
            // sideMenu.classList.remove('open');
            // overlay.classList.add('hidden');
        });
    }

    if (bottomNavItems) {
        bottomNavItems.forEach(item => {
            item.addEventListener('click', () => {
                const pageId = item.dataset.page;
                let title = pageId.split('-')[0].charAt(0).toUpperCase() + pageId.split('-')[0].slice(1);
                if (pageId === 'home-page') title = 'Home';
                if (pageId === 'profile-page') title = 'Profile';
                if (pageId === 'chat-page') title = 'Chat';
                if (pageId === 'settings-page') title = 'Settings';
                showPage(pageId, title, false); // Main pages are not sub-pages
            });
        });
    }

    if (darkModeToggle) {
        darkModeToggle.addEventListener('change', () => {
            document.body.classList.toggle('dark-mode');
            localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
        });
        // Check local storage for dark mode preference on load
        if (localStorage.getItem('darkMode') === 'true') {
            document.body.classList.add('dark-mode');
            darkModeToggle.checked = true;
        }
    }

    function renderQuotes(filter = "") {
        if (!quotesContainer) return;
        quotesContainer.innerHTML = '';
        const filteredQuotes = quotesData.filter(quote =>
            quote.text.toLowerCase().includes(filter.toLowerCase()) ||
            quote.author.toLowerCase().includes(filter.toLowerCase())
        );
        filteredQuotes.forEach(quote => {
            const card = document.createElement('div');
            card.className = 'quote-card';
            card.innerHTML = `
                <p>"${quote.text}"</p>
                <span>- ${quote.author}</span>
            `;
            quotesContainer.appendChild(card);
        });
    }
    if (searchQuotesInput) {
        searchQuotesInput.addEventListener('input', (e) => renderQuotes(e.target.value));
    }

    function loadProfileData() {
        if (!profilePicDisplay || !profileNameDisplay || !profileSubtitleDisplay || !profileAboutMeDisplay || !profileBadgesDisplay) return;
        profilePicDisplay.textContent = currentProfileData.initials;
        profileNameDisplay.textContent = currentProfileData.name;
        profileSubtitleDisplay.textContent = currentProfileData.subtitle;
        profileAboutMeDisplay.textContent = currentProfileData.about;
        profileBadgesDisplay.innerHTML = '';
        currentProfileData.badges.forEach(badgeLabel => {
            const badgeEl = document.createElement('span');
            const badgeClassKey = badgeColors[badgeLabel] || 'default';
            badgeEl.className = `badge ${badgeClassKey}`;
            badgeEl.textContent = badgeLabel;
            profileBadgesDisplay.appendChild(badgeEl);
        });
    }

    function loadMainEditProfileData() {
        if (!editProfilePicDisplay || !editProfileNameInput || !editProfileSubtitleInput || !editProfileAboutMeTextarea || !editProfileBadgesContainer) return;
        editProfilePicDisplay.textContent = currentProfileData.initials;
        editProfileNameInput.value = currentProfileData.name;
        editProfileSubtitleInput.value = currentProfileData.subtitle;
        editProfileAboutMeTextarea.value = currentProfileData.about;
        editProfileBadgesContainer.innerHTML = '';
        allBadges.forEach(badgeLabel => {
            const badgeEl = document.createElement('span');
            const badgeClassKey = badgeColors[badgeLabel] || 'default';
            badgeEl.className = `badge ${badgeClassKey}`;
            badgeEl.dataset.badgeLabel = badgeLabel; // Store label for saving
            if (currentProfileData.badges.includes(badgeLabel)) {
                badgeEl.classList.add('selected');
            }
            badgeEl.innerHTML = `${badgeLabel} <i class="fas fa-check"></i>`; // Check icon inside
            badgeEl.onclick = () => badgeEl.classList.toggle('selected');
            editProfileBadgesContainer.appendChild(badgeEl);
        });
    }

    function handleSaveMainProfile() {
        if (!editProfileNameInput || !editProfileSubtitleInput || !editProfileAboutMeTextarea || !editProfileBadgesContainer) return;
        currentProfileData.name = editProfileNameInput.value;
        currentProfileData.subtitle = editProfileSubtitleInput.value;
        currentProfileData.about = editProfileAboutMeTextarea.value;
        currentProfileData.initials = calculateInitials(currentProfileData.name);
        currentProfileData.badges = [];
        editProfileBadgesContainer.querySelectorAll('.badge.selected').forEach(bEl => {
            if (bEl.dataset.badgeLabel) { // Ensure it has the data attribute
                currentProfileData.badges.push(bEl.dataset.badgeLabel);
            }
        });
        showPage('profile-page', 'Profile', false); // Go back to profile page, not a sub-page
    }

    function handleCancelMainEditProfile() {
        showPage('profile-page', 'Profile', false); // Go back to profile page
    }

    function loadCategoryPage(categoryId) {
        const data = categoryData[categoryId];
        if (!categoryPageEl || !data) {
            showPage('home-page', 'Home'); // Fallback if data or element missing
            return;
        }
        showPage('category-page', data.label, true); // This is a sub-page
        if (categoryTabsContainer) categoryTabsContainer.innerHTML = ''; // Clear old tabs
        let firstTabId = null;

        Object.entries(data.tabs).forEach(([tabKey, _], index) => {
            const tabButton = document.createElement('button');
            tabButton.className = 'tab-link';
            tabButton.dataset.tab = tabKey;
            tabButton.textContent = tabKey.charAt(0).toUpperCase() + tabKey.slice(1);
            if (index === 0) {
                tabButton.classList.add('active');
                firstTabId = tabKey; // Capture the first tab
            }
            tabButton.onclick = () => displayCategoryTabContent(categoryId, tabKey);
            if (categoryTabsContainer) categoryTabsContainer.appendChild(tabButton);
        });

        // Add Questions tab if questions exist for the category
        if (data.questions && data.questions.length > 0) {
            const questionsTabButton = document.createElement('button');
            questionsTabButton.className = 'tab-link';
            questionsTabButton.dataset.tab = 'questions';
            questionsTabButton.textContent = 'Questions';
             if (!firstTabId) { // If no content tabs, make questions first
                 questionsTabButton.classList.add('active');
                 firstTabId = 'questions';
            }
            questionsTabButton.onclick = () => displayCategoryTabContent(categoryId, 'questions');
            if (categoryTabsContainer) categoryTabsContainer.appendChild(questionsTabButton);
        }

        // Display content for the first tab (or questions if it's first)
        if (firstTabId) displayCategoryTabContent(categoryId, firstTabId);
        else if (categoryContentArea) categoryContentArea.innerHTML = "<p>No content available.</p>"; // Fallback
    }

    function displayCategoryTabContent(categoryId, tabKey) {
        const data = categoryData[categoryId];
        if (!categoryPageEl || !data || !categoryContentArea) return;

        // Set active tab link
        if (categoryTabsContainer) {
            categoryTabsContainer.querySelectorAll('.tab-link').forEach(tab => {
                tab.classList.remove('active');
                if (tab.dataset.tab === tabKey) tab.classList.add('active');
            });
        }

        categoryContentArea.innerHTML = ''; // Clear previous content

        if (tabKey === "questions") {
            const questions = data.questions;
            if (questions && questions.length > 0) {
                const questionsWrapper = document.createElement('div');
                questions.forEach((qItem) => {
                    const qDiv = document.createElement('div');
                    qDiv.className = 'reflection-question';
                    qDiv.innerHTML = `<p>${qItem.text}</p><div class="options"><button data-answer="yes">Yes</button><button data-answer="no">No</button></div><div class="reflection-feedback hidden"></div>`;

                    qDiv.querySelectorAll('.options button').forEach(btn => {
                        btn.onclick = () => {
                            const feedbackDiv = qDiv.querySelector('.reflection-feedback');
                            if (feedbackDiv) {
                                feedbackDiv.textContent = btn.dataset.answer === 'yes' ? qItem.feedbackYes : qItem.feedbackNo;
                                feedbackDiv.classList.remove('hidden');
                            }
                            // Disable buttons after one is clicked for this question
                            qDiv.querySelectorAll('.options button').forEach(b => b.disabled = true);
                        };
                    });
                    questionsWrapper.appendChild(qDiv);
                });
                categoryContentArea.appendChild(questionsWrapper);
            } else {
                categoryContentArea.innerHTML = `<p>No reflection questions available for this topic yet.</p>`;
            }
        } else if (data.tabs && data.tabs[tabKey]) {
            categoryContentArea.innerHTML = data.tabs[tabKey];
        } else {
            categoryContentArea.innerHTML = `<p>Content for this section is not available yet.</p>`;
        }
    }

    // --- CHATBOT LOGIC (Fixed & Refined) ---
    let lastBotQuestionContext = null; // Stores { type: 'category_info', categoryId, categoryLabel }

    function handleSimpleGreetingsAndFarewells(lowerMessage) {
        if (lowerMessage.match(/\b(hello|hi|hey|hallo|good morning|good afternoon|good evening)\b/i)) {
            const greetings = [
                `Hello ${currentProfileData.name.split(' ')[0]}! How can I assist you today?`,
                "Hi there! What anti-discrimination topic are you interested in?",
                "Greetings! I'm here to help you learn about discrimination. What's on your mind?"
            ];
            return greetings[Math.floor(Math.random() * greetings.length)];
        }
        if (lowerMessage.match(/\b(bye|goodbye|see ya|see you later|farewell)\b/i)) {
            return "Goodbye! Feel free to return if you have more questions.";
        }
        if (lowerMessage.match(/\b(thanks|thank you|thx|cheers|appreciated)\b/i)) {
            const thanksResponses = [
                "You're welcome!", "Happy to help!", "No problem at all. Let me know if there's anything else."
            ];
            return thanksResponses[Math.floor(Math.random() * thanksResponses.length)];
        }
        if (lowerMessage.match(/\b(how are you|how's it going|sup|what's up)\b/i)) {
            return "I'm an AI assistant, functioning optimally and ready to help you learn!";
        }
        if (lowerMessage.match(/\b(your name|who are you)\b/i)) {
            return "I am your friendly anti-discrimination assistant, created to provide information and encourage reflection.";
        }
        return null;
    }

    function extractTextFromHtml(htmlString) {
        if (typeof htmlString !== 'string' || !htmlString) return "No specific details available in brief.";
        const tempDiv = document.createElement('div');
        tempDiv.innerHTML = htmlString;
        let text = tempDiv.textContent || tempDiv.innerText || "";
        text = text.replace(/\s\s+/g, ' ').trim(); // Consolidate multiple spaces and trim
        return text.substring(0, 220) + (text.length > 220 ? "..." : ""); // Slightly shorter for chat
    }

    const categoryAspectKeywords = {
        impact: ["impact", "effect", "consequence", "affect", "result", "repercussion"],
        solutions: ["solution", "solve", "fix", "address", "help with", "combat", "tackle", "overcome", "prevent", "mitigate"],
        problems: ["problem", "issue", "challenge", "difficulty", "downside", "concern", "obstacle"],
        laws: ["law", "legal", "legislation", "rule", "regulation", "policy", "act", "statute"]
    };

    function handleCategorySpecificQueries(lowerMessage) {
        for (const categoryKey in categoryData) {
            const category = categoryData[categoryKey];
            const categoryLabelLower = category.label.toLowerCase();
            const categoryTerms = [categoryLabelLower, categoryKey.replace('_', ' ')]; // e.g., "sexual orientation", "sexualorientation"
            if (categoryLabelLower.includes("sexual orientation")) categoryTerms.push("sogie"); // Add SOGIE for sexual orientation

            let foundCategoryTerm = categoryTerms.find(term => lowerMessage.includes(term));

            if (foundCategoryTerm) {
                // Check for specific aspects
                for (const aspect in categoryAspectKeywords) {
                    if (categoryAspectKeywords[aspect].some(kw => lowerMessage.includes(kw))) {
                        if (category.tabs && category.tabs[aspect]) {
                            const content = extractTextFromHtml(category.tabs[aspect]);
                            return `Regarding ${category.label} and its ${aspect}: ${content} You can find more details in the side menu.`;
                        }
                    }
                }
                // Check for questions query
                if (lowerMessage.includes("question") || lowerMessage.includes("reflect")) {
                    if (category.questions && category.questions.length > 0) {
                         return `I have reflection questions for ${category.label}. You can find them under the 'Questions' tab when you select '${category.label}' from the side menu.`;
                    } else {
                        return `I don't have specific reflection questions for ${category.label} at the moment, but you can explore its impact, solutions, and problems from the side menu.`;
                    }
                }
                // If only the category term is mentioned, it will be handled by handleGeneralCategoryQueries
            }
        }
        return null;
    }

    function handleGeneralCategoryQueries(lowerMessage) {
        for (const categoryKey in categoryData) {
            const category = categoryData[categoryKey];
            const categoryLabelLower = category.label.toLowerCase();
            const categoryTerms = [categoryLabelLower, categoryKey.replace('_', ' ')];
            if (categoryLabelLower.includes("sexual orientation")) categoryTerms.push("sogie");

            if (categoryTerms.some(term => lowerMessage.includes(term))) {
                // Check if an aspect keyword is ALSO present. If so, let handleCategorySpecificQueries handle it.
                let aspectPresent = false;
                for (const aspect in categoryAspectKeywords) {
                    if (categoryAspectKeywords[aspect].some(kw => lowerMessage.includes(kw))) {
                        aspectPresent = true;
                        break;
                    }
                }
                if (aspectPresent) continue; // Skip if a specific aspect is already in the query

                lastBotQuestionContext = { type: 'category_info', categoryId: category.id, categoryLabel: category.label };
                return `I have information about ${category.label}. Would you like to know about its impact, solutions, common problems, or relevant laws? You can also explore it from the side menu.`;
            }
        }
        return null;
    }

    function handleAppFeatureQueries(lowerMessage) {
        if (lowerMessage.match(/\b(my profile|about me)\b/i)) {
            return `You can view and edit your profile details from the 'Profile' tab. For account-specific settings like password changes, go to 'Settings' then 'Account'.`;
        }
        if (lowerMessage.match(/\b(my badge|what badge|badges)\b/i)) {
            const badgeText = currentProfileData.badges && currentProfileData.badges.length > 0 ?
                `Your current advocacy badges are: ${currentProfileData.badges.join(', ')}.` :
                "You currently don't have any advocacy badges selected.";
            return `${badgeText} You can manage these in 'Edit Profile' via the 'Profile' tab.`;
        }
        if (lowerMessage.match(/\b(my name|change name)\b/i) && !lowerMessage.includes("your name")) { // Avoid "what's your name"
             return `Your profile name is '${currentProfileData.name}'. You can change this in 'Account Settings' (under Settings) or from the 'Edit Profile' screen.`;
        }
        if (lowerMessage.match(/\b(dark mode|theme|appearance)\b/i)) {
            return "You can toggle Dark Mode in the 'Settings' page, accessible from the bottom navigation bar.";
        }
        if (lowerMessage.match(/\b(about app|what is this app)\b/i)) {
            return "This application is designed to raise awareness about various forms of discrimination. You can find more details in the 'About This App' section, accessible from the side menu or 'Settings'.";
        }
        if (lowerMessage.match(/\b(logout|log out|sign out)\b/i)) {
            return "You can log out using the 'Logout' button in the side menu or at the bottom of the 'Settings' page.";
        }
        if (lowerMessage.match(/\b(password.*(change|reset|forgot))\b/i)) {
            return "To change your password, go to 'Settings', then 'Account'. If you've forgotten your password and are logged out, use the 'Forgot Password?' link on the login screen.";
        }
        return null;
    }

    function handleQuoteRequests(lowerMessage) {
        if (lowerMessage.match(/\b(quote|inspiration|inspire me|saying|motivation)\b/i)) {
            if (quotesData.length > 0) {
                const randomQuote = quotesData[Math.floor(Math.random() * quotesData.length)];
                return `Here's an inspiring quote: "${randomQuote.text}" - ${randomQuote.author}`;
            }
            return "I'd love to share a quote, but I'm fresh out right now!";
        }
        return null;
    }

    function getFallbackResponse() {
        const fallbacks = [
            "I'm not sure I follow. Could you rephrase? You can ask about specific types of discrimination (e.g., 'impact of ageism') or app features.",
            "My apologies, I'm still learning. For detailed info, try the side menu or ask about 'solutions for gender discrimination', for example.",
            "I can help with discrimination topics, quotes, or app navigation. What are you looking for?",
            "Hmm, I didn't quite catch that. Try 'tell me about religious discrimination' or 'show me an inspiring quote'."
        ];
        return fallbacks[Math.floor(Math.random() * fallbacks.length)];
    }

    function getBotResponse(userMessage) {
        const lowerMessage = userMessage.toLowerCase().trim();
        let response = "";

        // 1. Handle contextual follow-up if a category was just mentioned by the bot
        if (lastBotQuestionContext && lastBotQuestionContext.type === 'category_info') {
            const { categoryId, categoryLabel } = lastBotQuestionContext;
            let aspectFound = null;

            for (const aspect in categoryAspectKeywords) {
                if (categoryAspectKeywords[aspect].some(kw => lowerMessage.includes(kw))) {
                    aspectFound = aspect;
                    break;
                }
            }

            if (aspectFound) { // User specified an aspect
                if (categoryData[categoryId] && categoryData[categoryId].tabs[aspectFound]) {
                    const content = extractTextFromHtml(categoryData[categoryId].tabs[aspectFound]);
                    response = `Okay, regarding the ${aspectFound} of ${categoryLabel}: ${content} More details are in the side menu.`;
                    lastBotQuestionContext = null; // Context used
                    return response;
                }
            } else if (lowerMessage.match(/\b(yes|yeah|sure|ok|okay|tell me more|more info|please do)\b/i)) {
                // User is affirmative but not specific, re-prompt for aspect
                response = `Great! For ${categoryLabel}, would you like to know about its impact, solutions, problems, or laws?`;
                // Keep context: lastBotQuestionContext remains set
                return response;
            } else if (lowerMessage.match(/\b(no|nope|never mind|cancel|stop|later|not now)\b/i)) {
                response = "Alright. Let me know if you have other questions!";
                lastBotQuestionContext = null; // Context cancelled
                return response;
            }
            // If it's not a clear follow-up to the context, the context might still be active.
            // The general handlers below might pick it up if no explicit context keyword is used.
            // If it's a completely new query, the context should be cleared by the new handler.
        }


        // 2. Simple direct interactions (greetings, thanks, etc.)
        response = handleSimpleGreetingsAndFarewells(lowerMessage);
        if (response) { lastBotQuestionContext = null; return response; }

        // 3. Queries about specific aspects of a category (e.g., "impact of gender discrimination")
        response = handleCategorySpecificQueries(lowerMessage);
        if (response) { lastBotQuestionContext = null; return response; }

        // 4. General queries about a category (e.g., "tell me about ageism") - this sets context
        if (!lastBotQuestionContext) { // Only set new context if one wasn't just handled by follow-up logic
            response = handleGeneralCategoryQueries(lowerMessage);
            if (response) return response; // This sets lastBotQuestionContext, so return immediately
        }

        // 5. Queries about app features
        response = handleAppFeatureQueries(lowerMessage);
        if (response) { lastBotQuestionContext = null; return response; }

        // 6. Requests for quotes
        response = handleQuoteRequests(lowerMessage);
        if (response) { lastBotQuestionContext = null; return response; }

        // 7. Fallback if nothing else matched
        lastBotQuestionContext = null; // Clear any lingering context before fallback
        return getFallbackResponse();
    }
    // --- END OF CHATBOT LOGIC ---

    let typingIndicatorTimeout;
    function addTypingIndicator() {
        if (!chatWindow) return;
        removeTypingIndicator(); // Clear any existing one
        const typingDiv = document.createElement('div');
        typingDiv.className = 'chat-message bot typing-indicator';
        typingDiv.innerHTML = '<span></span><span></span><span></span>';
        chatWindow.appendChild(typingDiv);
        if (chatWindow.lastChild) {
            chatWindow.lastChild.scrollIntoView({ behavior: 'smooth', block: 'end' });
        }
    }

    function removeTypingIndicator() {
        if (!chatWindow) return;
        const typingIndicator = chatWindow.querySelector('.typing-indicator');
        if (typingIndicator) {
            typingIndicator.remove();
        }
        if (typingIndicatorTimeout) {
            clearTimeout(typingIndicatorTimeout);
        }
    }

    function sendChatMessage() {
        if (!chatMessageInput || !chatWindow) return;
        const messageText = chatMessageInput.value.trim();
        if (messageText === '') return;

        addChatMessageToUI(messageText, 'user');
        chatMessageInput.value = '';
        addTypingIndicator();

        typingIndicatorTimeout = setTimeout(() => {
            removeTypingIndicator();
            const botResponse = getBotResponse(messageText);
            addChatMessageToUI(botResponse, 'bot');
        }, 1200 + Math.random() * 800);
    }

    function addChatMessageToUI(text, type) {
        if (!chatWindow) return;
        const messageDiv = document.createElement('div');
        messageDiv.className = `chat-message ${type}`;
        messageDiv.textContent = text;
        chatWindow.appendChild(messageDiv);
        if (chatWindow.lastChild) {
            chatWindow.lastChild.scrollIntoView({ behavior: 'smooth', block: 'end' });
        }
    }

    if (sendChatBtn) sendChatBtn.addEventListener('click', sendChatMessage);
    if (chatMessageInput) chatMessageInput.addEventListener('keypress', (e) => { if (e.key === 'Enter') sendChatMessage(); });

    if (settingsItems) {
        settingsItems.forEach(item => {
            item.addEventListener('click', () => {
                const pageId = item.dataset.page;
                const titleEl = item.querySelector('span'); // Get title from span inside
                const title = titleEl ? titleEl.textContent.trim() : 'Sub Page';
                showPage(pageId, title, true); // These are sub-pages
            });
        });
    }

    function loadAccountPageData() {
        if (!accountProfileNameInput || !accountProfileSubtitleInput || !accountProfileAboutTextarea || !accountEmailDisplay) return;
        accountProfileNameInput.value = currentProfileData.name;
        accountProfileSubtitleInput.value = currentProfileData.subtitle;
        accountProfileAboutTextarea.value = currentProfileData.about;
        accountEmailDisplay.textContent = currentProfileData.email;
    }

    function handleSaveAccountProfileInfo() {
        if (!accountProfileNameInput || !accountProfileSubtitleInput || !accountProfileAboutTextarea) return;
        currentProfileData.name = accountProfileNameInput.value.trim();
        currentProfileData.subtitle = accountProfileSubtitleInput.value.trim();
        currentProfileData.about = accountProfileAboutTextarea.value.trim();
        currentProfileData.initials = calculateInitials(currentProfileData.name);
        alert("Profile information updated!");
        // If user is on main profile or edit profile, update those too
        if (currentPageId === 'profile-page') loadProfileData();
        if (currentPageId === 'edit-profile-page') loadMainEditProfileData(); // This will also update initials display
    }

    if (saveAccountProfileBtn) saveAccountProfileBtn.addEventListener('click', handleSaveAccountProfileInfo);
    if (changePasswordBtn) {
        changePasswordBtn.addEventListener('click', () => {
            const currentPass = document.getElementById('current-password');
            const newPass = document.getElementById('new-password');
            const confirmPass = document.getElementById('confirm-new-password');
            if (currentPass && newPass && confirmPass && currentPass.value && newPass.value && newPass.value === confirmPass.value) {
                alert("Password update demo. (No actual change).");
                currentPass.value = ''; newPass.value = ''; confirmPass.value = '';
            } else if (newPass && confirmPass && newPass.value !== confirmPass.value) {
                 alert("New passwords do not match.");
            } else {
                alert("Please fill all password fields correctly.");
            }
        });
    }
    if (deactivateAccountBtn) {
        deactivateAccountBtn.addEventListener('click', () => {
            if (confirm("Are you sure you want to deactivate your account? This action is for demo purposes.")) {
                alert("Account deactivation demo. Logging out.");
                handleLogout();
            }
        });
    }

    function handleLogout() {
        // Reset user data to a default state
        currentProfileData = { name: "User", subtitle: "", about: "", badges: [], initials: "U", email: "" };
        if (emailInput) emailInput.value = ''; // Clear login form email
        if (passwordInput) passwordInput.value = ''; // Clear login form password
        // Clear signup form fields too, if they exist and were filled
        if (fullNameSignupInput) fullNameSignupInput.value = '';
        if (emailSignupInput) emailSignupInput.value = '';
        if (passwordSignupInput) passwordSignupInput.value = '';
        if (confirmPasswordSignupInput) confirmPasswordSignupInput.value = '';

        showPage('login-page', 'Log In');
    }
    if(logoutMenuBtn) logoutMenuBtn.addEventListener('click', handleLogout);
    if(logoutSettingsBtn) logoutSettingsBtn.addEventListener('click', handleLogout);

    // Initial page load logic
    if (loginPage && mainAppContent && forgotPasswordPage && signupPage) { // Ensure signupPage exists
        currentPageId = 'login-page'; // Default starting page
        previousPageId = 'login-page';
        showPage('login-page', 'Log In');
    } else {
        console.error("Critical page elements (login, main app, forgot password, or signup) not found for initial load.");
        document.body.innerHTML = "<p style='color:red;'>Error: Core app structure missing. Cannot initialize.</p>";
    }
});

window.onerror = function(message, source, lineno, colno, error) {
  console.error("GLOBAL ERROR:", { message, source, lineno, colno, errorObject: error });
  // alert("A global JavaScript error occurred. Check console for details."); // Optional user feedback
};

// --- Slideshow Global Variables and Functions (ensure these are accessible) ---
let slideIndexGlobal = 1;
let slideshowTimeoutGlobal;
const SLIDESHOW_INTERVAL = 4500;
const SLIDESHOW_RESTART_DELAY = 9000;

function displaySlide(index) {
    const homePage = document.getElementById('home-page');
    if (!homePage || homePage.classList.contains('hidden')) {
        clearTimeout(slideshowTimeoutGlobal); // Stop slideshow if not on home page
        return;
    }
    const slides = homePage.querySelectorAll(".slideshow-container .slide");
    const dots = homePage.querySelectorAll(".slide-dots .dot");
    if (slides.length === 0) return;
    if (index > slides.length) { slideIndexGlobal = 1; }
    if (index < 1) { slideIndexGlobal = slides.length; }
    slides.forEach(slide => slide.style.display = "none");
    if (dots.length > 0) {
        dots.forEach(dot => dot.classList.remove("active-dot"));
    }
    if (slides[slideIndexGlobal - 1]) {
        slides[slideIndexGlobal - 1].style.display = "block";
        // Force reflow for animation restart if needed
        slides[slideIndexGlobal - 1].classList.remove('fade');
        void slides[slideIndexGlobal - 1].offsetWidth; // Reflow
        slides[slideIndexGlobal - 1].classList.add('fade');
    }
    if (dots.length > 0 && dots[slideIndexGlobal - 1]) {
        dots[slideIndexGlobal - 1].classList.add("active-dot");
    }
}

function autoAdvanceSlides() {
    const homePage = document.getElementById('home-page');
    if (!homePage || homePage.classList.contains('hidden')) {
        clearTimeout(slideshowTimeoutGlobal);
        return;
    }
    slideIndexGlobal++;
    displaySlide(slideIndexGlobal);
    slideshowTimeoutGlobal = setTimeout(autoAdvanceSlides, SLIDESHOW_INTERVAL);
}

function plusSlides(n) {
    const homePage = document.getElementById('home-page');
    if (!homePage || homePage.classList.contains('hidden')) return;
    clearTimeout(slideshowTimeoutGlobal);
    slideIndexGlobal += n;
    displaySlide(slideIndexGlobal);
    slideshowTimeoutGlobal = setTimeout(autoAdvanceSlides, SLIDESHOW_RESTART_DELAY); // Restart auto-advance after manual interaction
}

function currentSlide(n) {
    const homePage = document.getElementById('home-page');
    if (!homePage || homePage.classList.contains('hidden')) return;
    clearTimeout(slideshowTimeoutGlobal);
    slideIndexGlobal = n;
    displaySlide(slideIndexGlobal);
    slideshowTimeoutGlobal = setTimeout(autoAdvanceSlides, SLIDESHOW_RESTART_DELAY);
}
