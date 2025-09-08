document.addEventListener('DOMContentLoaded', function() {
    console.log('nav.js 로드됨');
    
    const nav = document.querySelector('.nav');
    const navItems = document.querySelectorAll('.nav-item');
    
    console.log('nav:', nav);
    console.log('navItems 개수:', navItems.length);
    
    if (!nav) {
        console.error('nav 요소를 찾을 수 없습니다');
        return;
    }
    
    // 마커 요소 생성
    const marker = document.createElement('div');
    marker.id = 'marker';
    nav.appendChild(marker);
    
    // 각 nav-item에 hover 이벤트 추가
    navItems.forEach((item, index) => {
        const link = item.querySelector('a');
        const submenu = item.querySelector('.submenu');
        console.log(`아이템 ${index}:`, link.textContent.trim(), '서브메뉴:', submenu);
        
        if (submenu) {
            item.addEventListener('mouseenter', function() {
                console.log('마우스 진입:', link.textContent.trim());
                
                // 마커 위치 조정
                const linkRect = link.getBoundingClientRect();
                const navRect = nav.getBoundingClientRect();
                marker.style.left = (linkRect.left - navRect.left) + 'px';
                marker.style.width = linkRect.width + 'px';
                
                // 서브메뉴 표시
                submenu.style.display = 'block';
                submenu.style.opacity = '0';
                submenu.style.transform = 'translateX(-50%) translateY(-10px)';
                submenu.style.pointerEvents = 'none';
                
                // 애니메이션을 위한 약간의 지연
                setTimeout(() => {
                    submenu.style.opacity = '1';
                    submenu.style.transform = 'translateX(-50%) translateY(0)';
                    submenu.style.pointerEvents = 'auto';
                }, 10);
            });
            
            item.addEventListener('mouseleave', function() {
                console.log('마우스 나감:', link.textContent.trim());
                
                // 서브메뉴 숨기기
                submenu.style.opacity = '0';
                submenu.style.transform = 'translateX(-50%) translateY(-10px)';
                
                setTimeout(() => {
                    submenu.style.display = 'none';
                }, 200);
            });
        }
    });
    
    // nav 영역을 벗어나면 모든 서브메뉴 숨기기
    nav.addEventListener('mouseleave', function() {
        console.log('nav 영역 벗어남');
        const submenus = document.querySelectorAll('.submenu');
        submenus.forEach(submenu => {
            submenu.style.opacity = '0';
            submenu.style.transform = 'translateX(-50%) translateY(-10px)';
            setTimeout(() => {
                submenu.style.display = 'none';
            }, 200);
        });
    });
});