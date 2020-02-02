clear
clc
close all
% radiVals = [1,2,4,8,12,16,20,25,30,35,39,45,50,59];
radiVals = [1:100,105:5:200];
CLCD = zeros(length(radiVals),2);
Y = zeros(length(radiVals),1);
CP = cell(length(radiVals),1);
root = 'data/radius_all/';
for i = 1:length(radiVals)
    tempClCd = readmatrix([root,'CLCD',num2str(radiVals(i)),'.csv']);
    CLCD(i,:) = tempClCd(end,2:3);
    CP{i} = readmatrix([root,'CP',num2str(radiVals(i)),'.csv']);
    tempY = readmatrix([root,'Y+',num2str(radiVals(i)),'.csv']);
    Y(i,:) = tempY(end,1);
end
%%
close all
figure
subplot(2,1,1);
hold on
plot(radiVals,CLCD(:,1), 'LineWidth',1, 'Color','r')
% plot(radiVals2,CLCD(radiVals2,1), 'LineWidth',1, 'Color','r')
hold off
title("C_D")
xlabel("Domain Radius (m)")
ylabel("C_D")
ylim([0,0.1])
grid minor

subplot(2,1,2);
hold on
plot(radiVals,CLCD(:,2), 'LineWidth',1, 'Color','b')
% plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
hold off
title("C_L")
xlabel("Domain Radius (m)")
ylabel("C_L")
ylim([0,1]);
grid minor

figure
hold on
for i=20:20:120
    cp = CP(i);
    plot(cp(:,1), cp(:,2), 'LineWidth',1, 'Color','r')
end

% plot(radiVals, CP(:,2), 'LineWidth',1, 'Color','b')
% plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
hold off
title("Max Y+")
xlabel("Domain Radius (m)")
ylabel("Max Y+")
% ylim([0.5,1.5]);
grid on

