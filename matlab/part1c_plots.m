% clear
% clc
% close all
baseVals = [10:10:90,100:10:200,250:50:400];
CLCD = zeros(length(baseVals),2);
Y = zeros(length(baseVals),1);
CP = cell(length(baseVals),1);
root = 'data/base_all/';
for i = 1:length(baseVals)
    tempClCd = readmatrix([root,'CLCD',num2str(baseVals(i)),'.csv']);
    CLCD(i,:) = tempClCd(end,2:3);
    CP{i} = readmatrix([root,'CP',num2str(baseVals(i)),'.csv']);
    tempY = readmatrix([root,'Y+',num2str(baseVals(i)),'.csv']);
    Y(i,:) = tempY(end,1);
end

baseVals = baseVals/10000;
%%
close all
figure
subplot(2,1,1);
hold on
plot(baseVals,CLCD(:,1), 'LineWidth',1, 'Color','r')
% plot(radiVals2,CLCD(radiVals2,1), 'LineWidth',1, 'Color','r')
hold off
title("C_D")
xlabel("Base Size (m)")
ylabel("C_D")
ylim([0,0.08])
xlim([min(baseVals),max(baseVals)]);
grid minor

subplot(2,1,2);
hold on
plot(baseVals,CLCD(:,2), 'LineWidth',1, 'Color','b')
% plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
hold off
title("C_L")
xlabel("Base Size (m)")
ylabel("C_L")
ylim([0,1]);
xlim([min(baseVals),max(baseVals)]);
grid minor
% 
% subplot(3,2,1);
% hold on
% plot(baseVals,Y(:,1), 'LineWidth',1.5, 'Color','k')
% % plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
% hold off
% title("Max Y+")
% xlabel("Domain Radius (m)")
% ylabel("Max Y+")
% ylim([0.5,1.5]);
% xlim([min(baseVals),max(baseVals)]);
% grid on
% % 
% subplot(4,1,4);
% hold on
% plot(baseVals,Y(:,1), 'LineWidth',1.5, 'Color','k')
% % plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
% hold off
% title("Max Y+")
% xlabel("Domain Radius (m)")
% ylabel("Max Y+")
% ylim([0.5,1.5]);
% xlim([min(baseVals),max(baseVals)]);
% grid on

