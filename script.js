const STORAGE_KEY = "studentManagementRecords";
let students = JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]");

const form = document.getElementById("studentForm");
const records = document.getElementById("records");
const search = document.getElementById("search");
const message = document.getElementById("formMessage");

function grade(avg){
  if(avg >= 90) return "A+";
  if(avg >= 80) return "A";
  if(avg >= 70) return "B";
  if(avg >= 60) return "C";
  if(avg >= 50) return "D";
  return "F";
}

function save(){ localStorage.setItem(STORAGE_KEY, JSON.stringify(students)); }

function render(){
  const query = search.value.trim().toLowerCase();
  const filtered = students.filter(s => String(s.roll).includes(query) || s.name.toLowerCase().includes(query));
  records.innerHTML = "";

  if(!filtered.length){
    records.innerHTML = `<div class="empty">${students.length ? "No matching students found." : "No student records yet. Add your first student above."}</div>`;
  } else {
    filtered.forEach(s => {
      const avg = (s.java + s.dsa + s.maths) / 3;
      const card = document.createElement("article");
      card.className = "record";
      card.innerHTML = `<div><h3>${escapeHtml(s.name)} <span>#${s.roll}</span></h3><p>Java: ${s.java.toFixed(1)} · DSA: ${s.dsa.toFixed(1)} · Maths: ${s.maths.toFixed(1)} · Average: ${avg.toFixed(2)}</p></div><div class="record-right"><div class="grade">${grade(avg)}</div><button class="delete" data-roll="${s.roll}">Delete</button></div>`;
      records.appendChild(card);
    });
  }

  const total = students.length;
  const avg = total ? students.reduce((sum,s)=>sum+(s.java+s.dsa+s.maths)/3,0)/total : 0;
  const best = total ? students.reduce((best,s)=> gradeRank(grade((s.java+s.dsa+s.maths)/3)) > gradeRank(best) ? grade((s.java+s.dsa+s.maths)/3) : best, "F") : "—";
  document.getElementById("totalStudents").textContent = total;
  document.getElementById("overallAverage").textContent = avg.toFixed(2);
  document.getElementById("topGrade").textContent = best;
}

function gradeRank(g){ return ({"F":0,"D":1,"C":2,"B":3,"A":4,"A+":5})[g] ?? 0; }
function escapeHtml(value){ return value.replace(/[&<>\"]/g, c => ({"&":"&amp;","<":"&lt;",">":"&gt;","\"":"&quot;"})[c]); }

form.addEventListener("submit", e => {
  e.preventDefault();
  const roll = Number(document.getElementById("rollNumber").value);
  const name = document.getElementById("name").value.trim();
  const java = Number(document.getElementById("javaMarks").value);
  const dsa = Number(document.getElementById("dsaMarks").value);
  const maths = Number(document.getElementById("mathsMarks").value);

  if(students.some(s => s.roll === roll)){ message.textContent = "A student with this roll number already exists."; return; }
  students.push({roll,name,java,dsa,maths});
  save(); render(); form.reset(); message.textContent = "Student added successfully.";
  setTimeout(()=>message.textContent="",2500);
});

records.addEventListener("click", e => {
  if(!e.target.classList.contains("delete")) return;
  const roll = Number(e.target.dataset.roll);
  students = students.filter(s => s.roll !== roll);
  save(); render();
});

search.addEventListener("input", render);
document.getElementById("clearAll").addEventListener("click", () => {
  if(!students.length || confirm("Delete all student records?")){ students=[]; save(); render(); }
});

render();