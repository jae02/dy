document.addEventListener('DOMContentLoaded',()=>{
  const navLinks=document.querySelectorAll('.nav a');
  navLinks.forEach(a=>{
    a.addEventListener('click',()=>{
      navLinks.forEach(x=>x.classList.remove('active'));
      a.classList.add('active');
    });
  });
});


