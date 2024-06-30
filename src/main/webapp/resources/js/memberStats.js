document.addEventListener("DOMContentLoaded", function() {
    // pSBC 함수 정의
    const pSBC = (p,c0,c1,l) => {
        let r,g,b,P,f,t,h,i=parseInt,m=Math.round,a=typeof(c1)=="string";
        if(typeof(p)!="number"||p<-1||p>1||typeof(c0)!="string"||(c0[0]!='r'&&c0[0]!='#')||(c1&&!a))return null;
        if(!this.pSBCr)this.pSBCr=(d)=>{
            let n=d.length,x={};
            if(n>9){
                [r,g,b,a]=d=d.split(","),n=d.length;
                if(n<3||n>4)return null;
                x.r=i(r[3]=="a"?r.slice(5):r.slice(4)),x.g=i(g),x.b=i(b),x.a=a?parseFloat(a):-1
            }else{
                if(n==8||n==6||n<4)return null;
                if(n<6)d="#"+d[1]+d[1]+d[2]+d[2]+d[3]+d[3]+(n>4?d[4]+d[4]:"");
                d=i(d.slice(1),16);
                if(n==9||n==5)x.r=d>>24&255,x.g=d>>16&255,x.b=d>>8&255,x.a=m((d&255)/0.255)/1000;
                else x.r=d>>16,x.g>>8&255,x.b&255,x.a=-1
            }return x};
        h=c0.length>9,h=a?c1.length>9?true:c1=="c"?!h:false:h,f=this.pSBCr(c0),P=p<0,t=c1&&c1!="c"?this.pSBCr(c1):P?{r:0,g:0,b:0,a:-1}:{r:255,g:255,b:255,a:-1},p=P?p*-1:p,P=1-p;
        if(!f||!t)return null;
        if(l)r=m(P*f.r+p*t.r),g=m(P*f.g+p*t.g),b=m(P*f.b+p*t.b);
        else r=m((P*f.r**2+p*t.r**2)**0.5),g=m((P*f.g**2+p*t.g**2)**0.5),b=m((P*f.b**2+p*t.b**2)**0.5);
        a=f.a,t=t.a,f=a>=0||t>=0,a=f?a<0?t:t<0?a:a*P+t*p:0;
        if(h)return"rgb"+(f?"a(":"(")+r+","+g+","+b+(f?","+m(a*1000)/1000:"")+")";
        else return"#"+(4294967296+r*16777216+g*65536+b*256+(f?m(a*255):0)).toString(16).slice(1,f?undefined:-2)
    };

    // 성별 차트
    var genderCtx = document.getElementById('genderChart').getContext('2d');
    var genderStats = JSON.parse(genderStatsJson);
    var genderLabels = genderStats.map(stat => stat.user_gender);
    var genderData = genderStats.map(stat => stat.count);

    var genderChart = new Chart(genderCtx, {
        type: 'doughnut',
        data: {
            labels: genderLabels,
            datasets: [{
                data: genderData,
                backgroundColor: ['#3498db', '#e74c3c'],
                hoverBackgroundColor: ['#2980b9', '#c0392b']
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '70%',
            plugins: {
                legend: {
                    position: 'bottom',
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            var label = context.label || '';
                            var value = context.parsed;
                            var total = context.dataset.data.reduce((acc, data) => acc + data, 0);
                            var percentage = Math.round((value / total) * 100);
                            return label + ': ' + value + ' (' + percentage + '%)';
                        }
                    }
                }
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    });

    // 성별 통계 요약 업데이트
    var totalMembers = genderData.reduce((a, b) => a + b, 0);
    document.getElementById('totalMembers').textContent = totalMembers;
    document.getElementById('maleCount').textContent = genderData[genderLabels.indexOf('male')] || 0;
    document.getElementById('femaleCount').textContent = genderData[genderLabels.indexOf('female')] || 0;

    // SNS 차트
    var snsCtx = document.getElementById('snsChart').getContext('2d');
    var snsStats = JSON.parse(snsStatsJson);
    var snsLabels = snsStats.map(stat => stat.user_sns);
    var snsData = snsStats.map(stat => stat.count);

    var snsColors = {
        'naver': '#2DB400',  // 네이버 초록색
        'google': '#4285F4', // 구글 파란색
        'kakao': '#FEE500',  // 카카오 노란색
        'default': '#e74c3c' // 기본 색상 (다른 SNS가 있을 경우)
    };

    var snsChart = new Chart(snsCtx, {
        type: 'doughnut',
        data: {
            labels: snsLabels,
            datasets: [{
                data: snsData,
                backgroundColor: snsLabels.map(label => snsColors[label.toLowerCase()] || snsColors.default),
                hoverBackgroundColor: snsLabels.map(label => {
                    let color = snsColors[label.toLowerCase()] || snsColors.default;
                    return pSBC(-0.15, color); // 호버 시 약간 어두운 색상
                })
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: '60%',
            plugins: {
                legend: {
                    position: 'bottom',
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            var label = context.label || '';
                            var value = context.parsed || 0;
                            var total = context.dataset.data.reduce((a, b) => a + b, 0);
                            var percentage = ((value / total) * 100).toFixed(1);
                            return `${label}: ${value} (${percentage}%)`;
                        }
                    }
                }
            }
        }
    });

    // SNS 테이블 데이터 채우기
    var tableBody = document.getElementById('snsTableBody');
    var snsTotal = snsData.reduce((a, b) => a + b, 0);
    snsStats.forEach(stat => {
        var row = tableBody.insertRow();
        var cellSNS = row.insertCell(0);
        var cellCount = row.insertCell(1);
        var cellPercentage = row.insertCell(2);
        cellSNS.textContent = stat.user_sns;
        cellCount.textContent = stat.count;
        cellPercentage.textContent = ((stat.count / snsTotal) * 100).toFixed(1) + '%';
    });

    // SNS 요약 통계 채우기
    document.getElementById('totalUsers').textContent = snsTotal;
    document.getElementById('topSNS').textContent = snsLabels[snsData.indexOf(Math.max(...snsData))];

    // // 최근 30일 신규 가입자 수는 서버에서 받아와야 합니다.
    // // 여기서는 임시로 0으로 설정합니다.
    // document.getElementById('newUsers').textContent = '0';

    // 나이대 차트
    var ageCtx = document.getElementById('ageChart').getContext('2d');
    var ageStats = JSON.parse(ageStatsJson);
    var ageLabels = ['10대 이하', '20대', '30대', '40대', '50대', '60대 이상'];
    var ageData = ageLabels.map(label => {
        var stat = ageStats.find(stat => stat.age_group === label);
        return stat ? stat.count : 0;
    });

    var ageChart = new Chart(ageCtx, {
        type: 'bar',
        data: {
            labels: ageLabels,
            datasets: [{
                label: '가입자 수',
                data: ageData,
                backgroundColor: '#3498db',
                hoverBackgroundColor: '#2980b9'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            if (Number.isInteger(value)) {
                                return value;
                            }
                        },
                        stepSize: 1
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return context.parsed.y + '명';
                        }
                    }
                }
            }
        }
    });

    // 인기 관심태그 차트
    var tagCtx = document.getElementById('tagChart').getContext('2d');
    var tagStats = JSON.parse(tagStatsJson);
    var tagLabels = tagStats.map(stat => stat.tag_name);
    var tagData = tagStats.map(stat => stat.count);

    var tagChart = new Chart(tagCtx, {
        type: 'bar',
        data: {
            labels: tagLabels,
            datasets: [{
                label: '언급 횟수',
                data: tagData,
                backgroundColor: '#f39c12',
                hoverBackgroundColor: '#e67e22'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            if (Number.isInteger(value)) {
                                return value;
                            }
                        },
                        stepSize: 1
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return context.parsed.y + '회';
                        }
                    }
                }
            }
        }
    });
});
